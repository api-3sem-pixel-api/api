<template>
  <div class="login-container">
    <div class="login-box-container">
      <div class="login-box">
        <div class="header">
          <img src="@/assets/logo.png">
        </div>
        <input type="text" placeholder="Usuário" v-model="userInput.login" />
        <input type="password" placeholder="Senha" v-model="userInput.senha" />
        <button v-on:click="login()">Entrar</button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import http from "@/services/http";
import { useAuth } from '@/stores/auth';
import { Login } from './Models/login';
import { Vue } from 'vue-class-component';

export default class LoginView extends Vue {
  userInput: Login = {
    login: '',
    senha: '',
  };

  login() {
    const auth = useAuth();
    console.log(this.userInput)
    http.post('/login', this.userInput)
      .then((data: any) => {
        console.log(data);
        auth.setUser(data.idTipoUsuario);
        auth.setToken(data.token);
        auth.setIsAuth(true);
      });
  }
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.login-box-container {
  text-align: center;
}

.login-box {
  background: linear-gradient(0deg, rgb(68 144 221) 5.22%, #1F25C1 60.08%);
  box-shadow: 5px 5px 5px #c6c6c6;
  border-radius: 10px;
  padding: 20px;
  height: 80vh;
  flex-direction: column;
  display: flex;
  align-items: center;
  justify-content: space-around;
  width: 40vw;
}

.header {
  margin-bottom: 30px;
}

.login-box h2 {
  margin-bottom: 40px;
  width: 89px;
}

.login-box input {
  width: 80%;
  padding: 7.5px;
  margin: 25px 0;
  border: 1px solid #ccc;
  border-radius: 5px;
  color: #928B8B;
}

.login-box button {
  width: 50%;
  padding: 5px;
  background: #ffffff;
  color: blue;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  border: none;
  border-radius: 5px;
  font-size: 18px;
  cursor: pointer;
  margin-top: 35px;
}
</style>
