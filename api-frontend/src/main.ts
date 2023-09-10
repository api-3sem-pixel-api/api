import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import LoginView from './views/LoginView.vue'



createApp(App).component('LoginView',LoginView).use(router).mount('#app')
