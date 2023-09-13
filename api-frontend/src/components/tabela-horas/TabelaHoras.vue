<template>
    <div class="table-horas">
        <table class="table table-responsive no-wrap-table">
        <thead>
            <tr>
                <th scope="col">CR</th>
                <th scope="col">Cliente</th>
                <th scope="col">Projeto</th>
                <th scope="col">Inicio</th>
                <th scope="col">Fim</th>
                <th scope="col" v-if="podeGerenciarLancamentos">Solicitante</th>
                <th scope="col">Justificativa</th>
                <th scope="col">Status</th>
                <th scope="col" v-if="podeGerenciarLancamentos">Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="linha in linhas">
                <td>{{linha.cr}}</td>
                <td>{{linha.cliente}}</td>
                <td>{{linha.projeto}}</td>
                <td>{{ formatarData(linha.inicio)}}</td>
                <td>{{ formatarData(linha.fim)}}</td>
                <td v-if="podeGerenciarLancamentos">{{linha.solicitante}}</td>
                <td>{{linha.justificativa}}</td>
                <td>{{linha.status}}</td>
                <td v-if="podeGerenciarLancamentos">
                    <button class="btn btn-link text-success"><i class="fa fa-check" aria-hidden="true"></i></button>
                    <button class="btn btn-link text-danger"><i class="fa fa-window-close" aria-hidden="true"></i></button>
                </td>
            </tr>
        </tbody>
    </table>
    </div>
</template>
<script lang="ts">
import { Options, Vue } from 'vue-class-component';
import { ExtratoHoraLinha } from './extrato-hora-linha';


Options({
    props:['linhas', 'podeGerenciarLancamentos']
})
export default class TabelaHoras extends Vue {
    podeGerenciarLancamentos: boolean = false;
    linhas: ExtratoHoraLinha[] = [
        {
            cliente: 'Cliente 1',
            cr: 'Cr 1',
            fim: new Date(),
            id: 1,
            inicio: new Date(),
            justificativa: 'Solicitação externa',
            projeto: 'Projeto 1',
            solicitante: 'Luciano',
            status: 1
        }
    ];

    aprovar() : void {

    }

    reprovar() : void {

    }

    formatarData(data: Date) : string{
        const dataSplit = data.toISOString().split('T');
        
        if(!dataSplit)
            return '-';
        
        const datePart = dataSplit[0].split('-').reverse().join('/');
        const timePart = dataSplit[1].substring(0,8); 
        return datePart + ' ' + timePart;
    }
}
</script>
<style>
    .table-horas{
        width: 100%;
        overflow-x: auto;
        text-align: center;

        .no-wrap-table {
            text-wrap:nowrap;
        }
    }
</style>