<template>
  <div class="header">
    <div class="logo">
      <img src="../../assets/logo.png">
    </div>
  </div>
  <div id="sidenav">
    <SidebarItem :menu="menu" @close-all-but-this="closeMenus" v-for="menu in menus">
    </SidebarItem>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component';
import { MenuParent } from './menu';
import SidebarItem from './SidebarItem.vue';

@Options({
  props: ['menus'],
  components: {
    SidebarItem
  }
})
export default class Sidebar extends Vue {
  menus: MenuParent[] = [
  {
      icon: 'fas fa-home',
      description: 'Home',
      active: false,
      link: '/home',
      childs: []
    },
    {
      icon: 'fas fa-clock',
      description: 'Lançamento de Horas',
      active: false,
      link:'',
      childs: [
        { active: false, description: 'Lançamento', link: '/lancamentohoras' },
        { active: false, description: 'Aprovação/Reprovação', link: '/controlehoras' },
      ]
    }
  ];
  authorizationLevel: number = 1;
  
  created(): void {
    const currentUrl = this.$route.fullPath;
    this.menus.forEach(menu => {
      menu.active = currentUrl.includes(menu.link) && !!menu.link;
      this.activateSubItens(menu, currentUrl);
    })
  }

  private activateSubItens(menu: MenuParent, currentUrl: string) {
    menu.childs.map(subitem => {
      subitem.active = currentUrl.includes(subitem.link);
      if (subitem.active) menu.active = true;
    });
  }

  closeMenus(openedMenu: MenuParent) {
    this.menus.forEach(m => {
      m.active = openedMenu.description == m.description;
      m.childs.map(c => c.active = false);
    })
  }
}
</script>

<style scoped>
.header {
  background: linear-gradient(270deg, rgb(97, 156, 216) 5.22%, #1F25C1 60.08%);
  width: 100%;
  height: 76px;
  box-shadow: 5px 5px 5px #c6c6c6;

  .toggle-button {
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

#sidenav {
  height: 120vh;
  width: 260px;
  padding-top: 75px;
  float: left;
  overflow-x: hidden;
  box-shadow: 5px 5px 5px #c6c6c6;
  background: linear-gradient(0deg, rgb(97, 156, 216) 5.22%, #1F25C1 60.08%);
  transition: 0.5s;
  position: relative;

  .toggle-btn {
    top: 74%;
    left: 45%;
    position: absolute;
  }
}
</style>./Menu