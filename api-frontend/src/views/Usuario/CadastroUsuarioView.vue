<template>
  <div class="row mt-4">
    <div class="col-12">
      <h3>CONTROLE | USUARIO</h3>
      <hr>
    </div>
  </div>
  <div class="d-flex mt-3 justify-content-end mb-3">
    <button class="btn btn-outline-primary" @click="newUser()"> Cadastrar Usuário </button>
  </div>
  <div class="row">
    <table class="table table-responsive no-wrap-table">
      <thead>
        <tr>
          <th scope="col" class="text-left">Nome</th>
          <th scope="col" class="text-left">Email</th>
          <th scope="col" class="text-left">Telefone</th>
          <th scope="col" class="text-left">CPF</th>
          <th scope="col" class="text-center">Função</th>
          <th scope="col" class="text-center">Status</th>

          <th scope="col" class="text-center">Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(usuario, index) in usuarios" :key="index">
          <td>{{ usuario['nome'] }}</td>
          <td>{{ usuario['email'] }}</td>
          <td>{{ usuario['telefone'] }}</td>
          <td>{{ usuario['cpf'] }}</td>
          <td>{{ getFuncao(usuario.idTipoUsuario) }}</td>
          <td class="text-center d-flex" style="justify-content: center;">
            <div class="pill approved text-center text-wrap" :class="{
              approved: usuario['ativo'] == true,
            }">
              Ativo
            </div>
          </td>
          <td class="text-center">
            <button class="btn btn-link" @click="updateUser(usuario.id)">
              <i class="fa fa-pencil" aria-hidden="true"></i>
            </button>
            <button class="btn btn-link" @click="excludedUser(index)">
              <i class="fa fa-trash" aria-hidden="true"></i>
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>

  <!-- Modal de Atualização de Usuário -->
  <ModalUsuarioView :user-id="editUserId" @update-user-details="updateUserDetails" @close-modal="closeUpdateModal">
  </ModalUsuarioView>
</template>



<script lang="ts">
import http from '@/services/http';
import { PropType, defineComponent } from 'vue';
import ModalUsuarioView from '@/views/Usuario/ModalCadastroUsuario/ModalCadastroUsuarioView.vue';
import ModalCadastroUpdateUserView from './ModalCadastroUsuario/ModalCadastroUpdateUserView.vue';
import { enumUser } from '@/views/Usuario/enumUser';

export default defineComponent({
  name: "ControleUsuarioView",
  components: {
    ModalUsuarioView,
    ModalCadastroUpdateUserView
  },
  data() {
    return {
      usuarios: [] as Array<any>,
      enumUser: enumUser,
      editUserId: null as number | null
    };
  },
  created() {
    this.loadAllUser();
  },
  methods: {
    newUser() {
      var modal = document.getElementById("cadastro-user-modal")!;
      modal.style.display = "block";

    },
    async loadAllUser() {
      try {
        const response = await http.get('/usuario');
        this.usuarios = response.data;

        this.usuarios.forEach((usuario) => {
          usuario.funcao = enumUser[usuario.tipoUsuario];
        });
      } catch (err) {
        alert('Algo deu errado, tente novamente mais tarde.');
      }
    },
    getFuncao(idTipoUsuario: number): string {
      switch (idTipoUsuario) {
        case enumUser.Colaborador:
          return 'Colaborador';
        case enumUser.Administrador:
          return 'Administrador';
        case enumUser.Gestor:
          return 'Gestor';
        default:
          return 'Função Desconhecida';
      }
    },
    updateUser(userId: number) {
      this.editUserId = userId;
      var modal = document.getElementById("update-user-modal");
      if (modal) {
        modal.style.display = "block";
      } else {
        console.error("Elemento não encontrado no DOM.");
      }
    },

    async updateUserDetails(updatedUser: any) {
      try {
        await http.put(`/usuario/${this.editUserId}`, updatedUser);

        const userIndex = this.usuarios.findIndex((u) => u.id === this.editUserId);
        if (userIndex !== -1) {
          this.usuarios[userIndex] = updatedUser;
        }

        this.closeUpdateModal();
      } catch (error) {
        alert('Erro ao atualizar o usuário. Tente novamente mais tarde.');
      }
    },
    closeUpdateModal() {
      this.editUserId = null;
      var modal = document.getElementById("update-user-modal");
      if (modal) {
        modal.style.display = "none";
      }
    },
    async excludedUser(index: number) {
      const usuario = this.usuarios[index];
      try {
        await http.delete(`/usuario/${usuario.id}`);
        this.usuarios.splice(index, 1);
      } catch (error) {
        alert('Algo deu errado, tente novamente mais tarde.');
      }
    }
  }
});
</script>

<style scoped>
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

  .pill {
    border-radius: 30px;
    width: 140px;
    color: white;

    &.approvedGestor {
      background-color: #fac02d;
    }

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

}

.r-ml-2 {
  margin-left: 15px;
}
</style>