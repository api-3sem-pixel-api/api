<template>
  <div class="d-flex justify-content-end mb-3">
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
          <td class="text-center">
            <div class="pill text-center text-wrap" :class="{ 'approved': usuario.ativo, 'canceled': !usuario.ativo }">{{ usuario.ativo ? 'Ativo' : 'Inativo' }}</div>
          </td>
          <td class="text-center">
            <button class="btn btn-link" @click="updateUser(usuario.id)">
              <i class="fa fa-pencil" aria-hidden="true"></i>
            </button>
            <button class="btn btn-link" @click="inativarUsuario(usuario['id'])">
              <i class="fa fa-trash" aria-hidden="true"></i>
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>

  <!-- Modal de Atualização de Usuário -->
  <ModalUsuarioView
    :user-id="editUserId"
    @update-user-details="updateUserDetails"
    @close-modal="closeUpdateModal"
  ></ModalUsuarioView>
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
      editUserId: null as number | null,
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
          usuario.funcao = this.getFuncao(usuario.idTipoUsuario);
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
    async inativarUsuario(id: number) {
      const usuario = this.usuarios.find((usuario) => usuario.id === id);
      console.log('ID a ser excluído:', id);

      if (usuario) {
        console.log('Usuario encontrado:', usuario);

        try {
          if (usuario.ativo) {
            console.log('Usuario ativo, marcando como inativo:', );
            await http.delete(`/usuario/${usuario.id}`);
            usuario.ativo = false;
            alert('Usuario marcado como Inativo');
          } else {
            console.log('Usuario já está Inativo:', usuario);
            alert('Usuario já está Inativo');
          }
        } catch (error) {
          console.error('Erro ao excluir o Usuario:', error);
          alert('Algo deu errado, tente novamente mais tarde.');
        }
      } else {
        console.log('Usuario não encontrado:', id);
        alert('Usuario não encontrado');
      }
    }
  }
});
</script>

<style scoped>
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
</style>
