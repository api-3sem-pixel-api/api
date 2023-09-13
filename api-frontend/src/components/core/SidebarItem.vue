<template>
  <div class="item" :class="{ active: menu.active }">
    <div class="item-content">
      <router-link :to="menu.link ? menu.link : ''" class="item-main" @click="$emit('closeAllButThis', toggle())">
        <i :class="menu.icon"></i>
        <span> {{ menu.description }} </span>
      </router-link>

      <div class="dropdown" v-if="menu.active" v-for="dropdownItem in menu.childs">
        <router-link :to="dropdownItem.link" @click="$emit('changePage', changePage(dropdownItem))">
          <a class="dropdown-link">
            <span class="description" :style="{ 'text-decoration': dropdownItem.active ? 'underline' : 'none' }">
              {{ dropdownItem.description }}
            </span>
          </a>
        </router-link>
      </div>
    
    </div>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component';
import { MenuChild, MenuParent } from './menu';

@Options({
  props: ['menu'],
})
export default class SidebarItem extends Vue {
  menu!: MenuParent;

  toggle() : MenuParent {
    this.menu.active = !this.menu.active;
    return this.menu;
  }

  changePage(subitem: MenuChild): MenuChild {
    this.menu.childs.forEach(x => {
      x.active = subitem.description == x.description;
    });

    return subitem;
  }
}
</script>

<style  scoped>
.item {
  display: flex;
  align-items: center;
  width: 100%;
  min-height: 45px;
  margin-bottom: 5px;
}

.item-content {
  width: 100%;
}

.item-main {
  cursor: pointer;
  width: 100%;
}

.active {
  color: #1F25C1;
  background-color: #fff;
  transition: 0.15s linear;
  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
}

.item:not(.active) {
  color: #fff !important;
  background: #1F25C1;
}

.dropdown {
  padding: 8px 0 0 50px;
  position: relative;

  .dropdown-link {
    cursor: pointer;
    display: flex;
    width: 100%;

    .description {
      margin: 0 5px 0 5px;
    }
  }
}


svg {
  margin: 0 15px 0 20px;
}
</style>./Menu