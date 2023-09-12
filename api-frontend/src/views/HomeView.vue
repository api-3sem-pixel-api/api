<template>
  <div class="page">
    <Sidebar></Sidebar>
    <div class="content">
      <div class="card">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import Sidebar from '@/components/core/Sidebar.vue';

@Options({
  components: {
    Sidebar
  }
})
export default class HomeView extends Vue {
};
</script>

<style scoped>
.page {
  width: 100%;
  background-color: rgba(235, 235, 235, 0.925);
}

.content {
  height: inherit;
  margin: 75px 0 0 300px;
  height: 100vh;
  width: calc(100vw - 350px);
}

.card {
  background-color: white;
  box-shadow: 0px 0px 5px #dddddd;
  height: 95%;
  border-radius: 5px;
  width: 100%;
}
</style>
