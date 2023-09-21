import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Login from '@/views/Login/LoginView.vue' 
import Home from '@/views/HomeView.vue' 
import LancamentoHorasView from '@/views/LancamentoHoras/LancamentoHorasView.vue'




const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/authorized",
    name: "Home",
    component: Home,
    children:[
      {
        path: "/lancamentohoras",
        name: "LancamentoHoras",
        component:LancamentoHorasView,
      }
    ]
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
