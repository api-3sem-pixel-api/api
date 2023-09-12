<template>
  <div class="login-container">
    <div class="login-box-container">
      <div class="login-box">
        <h2>Login</h2>
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
  background: linear-gradient(to bottom right, #0c42f7,10%, #4E7ACB);


  border-radius: 10px;
  padding: 20px;
  margin: 41px 0 41px;
  padding-bottom: 70px;
  padding-top: 20px;
  max-width: 548px;
  height:  598px;
  width: 100%;
}

.login-box h2 {
  margin-bottom: 40px;
  width: 89px;
height: 38.943px;

}

.login-box input {
  width: 80%;
  max-width: 80%;
  padding: 20px;
  
  margin-top: 80px;
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
  margin-top: 250px;
}
</style>
