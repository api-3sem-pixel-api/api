<template>
    <div class="table-horas">
        <table class="table table-responsive no-wrap-table">
            <thead>
                <tr>
                    <th scope="col" class="text-left">Modalidade</th>
                    <th scope="col" class="text-left">CR</th>
                    <th scope="col" class="text-left">Cliente</th>
                    <th scope="col" class="text-left">Projeto</th>
                    <th scope="col" class="text-left">Inicio</th>
                    <th scope="col" class="text-left">Fim</th>
                    <th scope="col" class="text-left" v-if="podeGerenciarLancamentos">Solicitante</th>
                    <th scope="col" class="text-left">Justificativa</th>
                    <th scope="col" class="text-center">Status</th>
                    <th scope="col" class="text-center" v-if="podeGerenciarLancamentos">Ações</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="linha in horas">
                    <td>{{ linha.modalidade }}</td>
                    <td>{{ linha.cr }}</td>
                    <td>{{ linha.cliente }}</td>
                    <td>{{ linha.projeto }}</td>
                    <td>{{ formatarData(linha.inicio) }}</td>
                    <td>{{ formatarData(linha.fim) }}</td>
                    <td v-if="podeGerenciarLancamentos">{{ linha.solicitante }}</td>
                    <td>{{ linha.justificativa }}</td>
                    <td>
                        <div class="pill text-center" :class="{
                            waiting: linha.status == 1,
                            approved: linha.status == 2,
                            reproved: linha.status == 3,
                            canceled: linha.status == 4
                        }">{{ obterDescricaoStatus(linha.status) }}</div>
                    </td>
                    <td v-if="podeGerenciarLancamentos" class="text-center">
                        <button class="btn btn-link text-success" @click="aprovar(linha)">
                            <i class="fa fa-check" aria-hidden="true"></i>
                        </button>
                        <button class="btn btn-link text-danger" @click="reprovar(linha)">
                            <i class="fa fa-window-close" aria-hidden="true"></i>
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

    <ModalMotivo :idLancamento="idLancamentoReprova" :statusAtual="statusReprova"></ModalMotivo>
</template>

<script lang="ts">
import { PropType, defineComponent } from 'vue';
import { ExtratoHoraLinha } from './extrato-hora-linha';
import ModalMotivo from '../ModalMotivo/ModalMotivo.vue';
import http from '@/services/http';


export default defineComponent({
    props: {
        podeGerenciarLancamentos: Boolean,
        horas: Array as PropType<ExtratoHoraLinha[]>
    },
    components: {
        ModalMotivo
    },
    data(){
        return {
            idLancamentoReprova: 0,
            statusReprova: 0
        }
    },
    methods: {
        aprovar(linha: ExtratoHoraLinha): void {
            const horaParaAprovar = {
                idLancamento: linha.id,
                status: 2,
                justificativa: null
            };

            http.put('/lancamentohoras', horaParaAprovar)
                .then(r => {
                    alert('Hora aprovada com sucesso.');
                })
                .catch(err => {
                    alert('Algo deu errado tente novamente mais tarde.')
                });
        },

        reprovar: function (linha: ExtratoHoraLinha): void {
            this.idLancamentoReprova = linha.id;
            this.statusReprova = linha.status;
            const modal = document.getElementById("reprovar-modal")!;
            modal.style.display = "block";
        },

        obterDescricaoStatus(statusId: number) {
            switch (statusId) {
                case 1: return 'Em Aprovação';
                case 2: return 'Aprovada';
                case 3: return 'Reprovada';
                case 4: return 'Cancelada';
                default: return '-'
            }
        },

        formatarData(toFormatDate: Date | undefined | null): string {
            if (!toFormatDate) return '';

            const data = new Date(toFormatDate);
            const dataSplit = data.toISOString().split('T');

            if (!dataSplit)
                return '-';

            const datePart = dataSplit[0].split('-').reverse().join('/');
            const timePart = dataSplit[1].substring(0, 5);
            return datePart + ' ' + timePart;
        }
    }
})
</script>
<style>
.table-horas {
    width: 100%;
    overflow-x: auto;

    .no-wrap-table {
        text-wrap: nowrap;
    }
}

.pill {
    border-radius: 30px;
    width: 130px;
    color: white;
    text-align: center;

    &.approved {
        background-color: #26fc29;
    }

    &.waiting {
        background-color: gainsboro;
    }

    &.reproved,
    &.canceled {
        background-color: red;
    }
}
</style>