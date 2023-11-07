<template>
  <div class="r-modal" id="update-user-modal">
    <div class="r-modal-content">
      <div class="modal-header d-flex align-items-baseline">
        <h4>Edição de Usuário</h4>
        <span class="close" @click="close">&times;</span>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-12">
            <div class="form-group">
              <label for="nome">Nome</label>
              <input type="text" class="form-control" id="nome" :value="localNome" @input="updateNome" />
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-12">
            <div class="form-group">
              <label for="telefone">Telefone</label>
              <input type="text" class="form-control" id="telefone" :value="localTelefone" @input="updateTelefone" />
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-12">
            <div class="form-group">
              <label for="email">Email</label>
              <input type="text" class="form-control" id="email" :value="localEmail" @input="updateEmail" />
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-12">
            <div class="form-group">
              <label for="cpf">CPF</label>
              <input type="text" class="form-control" id="cpf" :value="localCpf" @input="updateCpf" />
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-12">
            <div class="form-group">
              <label for="funcao">Função</label>
              <select class="form-select" id="funcao" :value="localFuncao" @input="updateFuncao">
                <option value="Colaborador">Colaborador</option>
                <option value="Administrador">Administrador</option>
                <option value="Gestor">Gestor</option>
              </select>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-12">
            <div class="form-group">
              <label for="status">Status</label>
              <select class="form-select" id="status" v-model="localStatus">
                <option value="ativo">Ativo</option>
                <option value="inativo">Inativo</option>
              </select>
            </div>
          </div>
        </div>

        <div class="row mt-4">
          <div class="col">
            <button type="button" @click="save" class="btn btn-success">Salvar</button>
            <button type="button" @click="close" class="btn btn-link r-ml-2">Cancelar</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from '@/services/http';

export default {
  props: {
    nome: String,
    email: String,
    telefone: String,
    cpf: String,
    funcao: String,
    showModal: Boolean,
    errorMessage: String,
    status: Boolean,
    userId: Number,
  },
  data() {
    return {
      localNome: this.nome,
      localEmail: this.email,
      localTelefone: this.telefone,
      localCpf: this.cpf,
      localFuncao: this.funcao,
      localStatus: this.status, 
    };
  },
  methods: {
    close() {
      const modal = document.getElementById("update-user-modal");
      if (modal) {
        modal.style.display = "none";
      }
      this.clear();
    },
    clear() {
      this.localNome = "";
      this.localEmail = "";
      this.localTelefone = "";
      this.localCpf = "";
      this.localFuncao = "";
    },
    updateNome(event) {
      this.localNome = event.target.value;
    },
    updateTelefone(event) {
      this.localTelefone = event.target.value;
    },
    updateEmail(event) {
      this.localEmail = event.target.value;
    },
    updateCpf(event) {
      this.localCpf = event.target.value;
    },
    updateFuncao(event) {
      this.localFuncao = event.target.value;
    },
    async save() {
      const updatedUser = {
        nome: this.localNome,
        email: this.localEmail,
        telefone: this.localTelefone,
        cpf: this.localCpf,
        funcao: this.localFuncao,
        status: this.localStatus,
      };

      try {
        await http.put(`/usuario/${this.userId}`, updatedUser);

        const userIndex = this.usuarios.findIndex((u) => u.id === this.userId);
        if (userIndex !== -1) {
          this.usuarios[userIndex] = updatedUser;
        }

        this.close();
      } catch (error) {
        alert('Erro ao atualizar o usuário. Tente novamente mais tarde.');
        console.log(error);
      }
    },
  },
};
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
}

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

.r-ml-2 {
  margin-left: 15px;
}
</style>
