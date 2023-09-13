import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

const app = createApp(App)
app.config.globalProperties.$filters = {
    formatDate(value: Date) {
      return value.toISOString();
    }
}

app.use(router).mount('#app')
