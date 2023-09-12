<template>
  <Sidebar></Sidebar>
  <div>
    Teste
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
  .teste{
    float: right;
  }
</style>
