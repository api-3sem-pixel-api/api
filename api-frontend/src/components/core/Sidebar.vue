<template>
  <div class="header">
    <div class="logo">
      <img src="../../assets/logo.png">
    </div>
  </div>
  <div class="sidenav">
    <SidebarItem :menu="menu" @close-all-but-this="closeMenus" @change-page="changePage" v-for="menu in menus">
    </SidebarItem>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component';
import { MenuChild, MenuParent } from './Menu';
import SidebarItem from './SidebarItem.vue';

@Options({
  props:['menus'],
  components: {
    SidebarItem
  }
})
export default class Sidebar extends Vue {
  menus: MenuParent[] = [
    {
      icon: 'fas fa-home',
      description: 'Home',
      active: true,
      link: '/home',
      childs: []
    },
    {
      icon: 'fas fa-clock',
      description: 'Lançamento de Horas',
      active: false,
      link:'',
      childs: [
        { active: false, description: 'Lançamento', link: '/home' },
        { active: false, description: 'Aprovação/Reprovação', link: '/home' },
      ]
    }
  ];

  changePage(current: MenuChild){;
    this.menus.forEach(m => {
      m.childs.map(c => c.active = current.description == c.description);
    })
  }

  closeMenus(openedMenu: MenuParent){
    this.menus.forEach(m => {
      m.active = openedMenu.description == m.description;
      m.childs.map(c => c.active = false);
    })
  }
}
</script>

<style scoped>
.header {
  /* background: #3A78F2; */
  background: linear-gradient(270deg, rgb(97, 156, 216) 5.22%, #1F25C1 60.08%);
  width: 100%;
  height: 76px;
  position: relative;
  box-shadow: 5px 5px 5px #c6c6c6;

  .toggle-button{
    display: flex;
  }

  .logo {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    height: inherit;
    justify-content: center;
  }
}

.sidenav {
  /* background: #3A78F2; */
  background: linear-gradient(0deg, rgb(97, 156, 216) 5.22%, #1F25C1 60.08%);
  font-size: 16px;
  height: 100vh;
  box-shadow: 5px 5px 5px #c6c6c6;
  width: 260px;
  position: relative;
  float: left;
  z-index: 9;
  overflow-x: hidden;
  padding-top: 75px;
  transition: 0.5s;
}
</style>./Menu