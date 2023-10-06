<template>
    <div id="cadastro-user-modal" class="r-modal">
        <!-- Modal content -->
        <div class="r-modal-content">
            <div class="modal-header d-flex align-items-baseline">
                <h4>Cadastro de Usuario</h4>
                <span class="close" @click="close">&times;</span>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-12">
                        <div class="form-group">
                            <label for="nome">Nome</label>
                            <input type="text" class="form-control" id="nome" v-model="nome">
                        </div>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-12">
                        <div class="form-group">
                            <label for="telefone">Telefone</label>
                            <input type="text" class="form-control" id="telefone" v-model="telefone">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="text" class="form-control" id="email" v-model="email">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <div class="form-group">
                            <label for="CPF">CPF</label>
                            <input type="text" class="form-control" id="CPF" v-model="CPF">
                        </div>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-12">
                        <div class="form-group">
                            <label for="funcao">Funcao</label>
                            <input type="text" class="form-control" id="funcao" v-model="funcao">
                        </div>
                    </div>
                </div>
                <div class="row mt-4">
                    <div class="col">
                        <button type="button" @click="save()" class="btn btn-success">Salvar</button>
                        <button type="button" @click="close()" class="btn btn-link r-ml-2">Cancelar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import http from '@/services/http';
import { defineComponent } from 'vue';

export default defineComponent({
    name: 'ModalCadastroCrView',
    data() {
        return {
            nome: '',
            email: '', 
            telefone:'',
            CPF: '',
            funcao:''
        }
    },
    methods: {
        save(){
            const userToCreate = {
                nome: this.nome,
                email: this.email,
                telefone:this.telefone, 
                CPF: this.CPF,
                funcao: this.funcao,
                idCr: 0
            }

            http.post('/usuario', userToCreate)
                .then(_ => alert ('Usuario salvo com sucesso!!!'))
                .catch(_ => alert('Algo deu errado. Tente novamente mais tarde.'))
                .finally(() => {
                    this.emitUpdateTable();
                    this.close()
                });
        },
        emitUpdateTable(){
            this.$emit('update-table');
        },
        close() {
            var modal = document.getElementById("cadastro-user-modal")!;
            modal.style.display = "none";
            this.clear();
        },
        clear(){
            this.nome = '';
            this.email = '';
            this.telefone = '';
            this.CPF = '';
            this.funcao = '';
        }
    }
})
</script>

<style lang="css">
.r-modal {
    display: none;
    position: fixed;
    z-index: 1;
    padding-top: 100px;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgb(0, 0, 0);
    background-color: rgba(0, 0, 0, 0.4);

    .r-modal-content {
        background-color: #fefefe;
        margin: auto;
        padding: 20px;
        border: 1px solid #888;
        width: 630px;
    }

    .close {
        color: #aaaaaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
    }

    .close:hover,
    .close:focus {
        color: #000;
        text-decoration: none;
        cursor: pointer;
    }

    button {
        color: white;
    }
}

.r-ml-2 {
    margin-left: 15px;
}
</style>    