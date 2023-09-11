<template>
  <div class="header"></div>
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
  background: #3A78F2;
  width: 100%;
  height: 76px;
  position: absolute;
  top:0;
  left:0;
}

.sidenav {
  background: #3A78F2;
  font-size: 16px;
  height: 100%;
  width: 260px;
  position: fixed;
  z-index: 10;
  overflow-x: hidden;
  padding-top: 75px;
  transition: 0.5s;
}
</style>./Menu