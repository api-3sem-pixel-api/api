<template>
  <template>
    <div id="reprovar-modal" class="r-modal">

        <!-- Modal content -->
        <div class="r-modal-content">
            <div class="modal-header d-flex align-items-baseline">
                <h4>Explique o motivo</h4>
                <span class="close">&times;</span>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col">
                        <input  placeholder="Nome"  v-model="userInput.nome" style="width: 800px;" type="text" class="form-control" />
                  
                    </div>
                    <div class="col">
                        <input  placeholder="Email"   v-model="userInput.email" style="width: 800px;" type="text" class="form-control" />
                    
                    </div>
                    <div class="col">
                        <input  placeholder="Funcao"  v-model="userInput.funcao" style="width: 800px;" type="text" class="form-control" />
                    </div>
                    <div class="col">
                        <input  placeholder="CPF"  v-model="userInput.CPF" style="width: 800px;" type="text" class="form-control" />
                  
                    </div>
                </div>
                <div class="row mt-4">
                    <div class="col">
                        <button type="button"  class="btn btn-danger">Cancelar</button>
                        <button type="button"  class="btn btn-link r-ml-2">Cadastrar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { useRouter } from 'vue-router';
import http from "@/services/http";
import { cadastrousuario } from './Models/cadastrousuario';
import { Vue } from 'vue-class-component';
import router from "@/router";

export default class CadastroUsuarioView extends Vue{
  userInput: cadastrousuario = {
    nome:'',
    email:'',
    funcao:'',
    telefone:'',
    CPF:'',
  };
  cad(){


  }
}

</script>
