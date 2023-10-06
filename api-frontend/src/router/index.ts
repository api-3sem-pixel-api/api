import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Login from '@/views/Login/LoginView.vue'
import Home from '@/views/HomeView.vue'
import LancamentoHorasView from '@/views/LancamentoHoras/LancamentoHorasView.vue'
import CadastroUsuarioView from '@/views/Usuario/CadastroUsuarioView.vue'

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
    children: [
      {
        path: "lancamentohoras", 
        name: "LancamentoHoras",
        component: LancamentoHorasView,
      },
      {
        path: "/cadastrousuario",
        name: "CadastroUsuario",
        component: CadastroUsuarioView,
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
