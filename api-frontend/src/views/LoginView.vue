<template>
  <div class="login-container">
    <div class="login-box-container">
      <div class="login-box">
        <div class="header">
              <img src="../assets/logo.png">
        </div>
        <input type="text" placeholder="Usuário" v-model="username" />
        <input type="password" placeholder="Senha" v-model="password" />
        <button @click="login">Entrar</button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import  http  from "@/services/http";
import { useAuth } from '@/stores/auth';

export default defineComponent({
  data() {
    return {
      username: '',
      password: '',
    };
  },
  methods: {
    async login() {
      const auth = useAuth();

      try {
        const user = {
          email: 'teste94@ramos.com',
          password: '123',
        };

        const { data } = await http.post('/auth', user);
        auth.setToken(data.token);
        auth.setUser(data.user);
        auth.setIsAuth(true);
      } catch (error) {
        console.log(error);
      }
    },
  },
});
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
}

.login-box-container {
  text-align: center;
}

.login-box {
  background: linear-gradient(0deg, rgb(97, 156, 216) 5.22%, #1F25C1 60.08%);
  box-shadow: 5px 5px 5px #c6c6c6;


  border-radius: 10px;
  padding: 20px;
  margin: 40px 0 100px;
  padding-bottom: 70px;
  padding-top: 20px;
  max-width: 548px;
  height:  598px;
 
}

.login-box h2 {
  margin-bottom: 40px;
  width: 89px;
  height: 38.943px;

}

.login-box header logo{

  width: 89px;
height: 38.943px;
flex-shrink: 0;
margin-bottom: 100000px;
}

.login-box input {
  width: 80%;
  max-width: 80%;
  padding: 20px;
  margin: 50px 0 50px;

  border: 1px solid #ccc;
  border-radius: 15px;
  color: #928B8B;
  font-family: Poppins;
  font-size: 14px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
}

.login-box button {
  width: 100%;
  max-width: 50%;
  padding: 10px;
  background: #ffffff;
  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.35));

  color: #d3baba;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  border: none;
  border-radius: 5px;
  font-size: 18px;
  cursor: pointer;
  margin-top: 150px;
}
</style>
