<template>
  <Sidebar></Sidebar>
  <div class="content"></div>
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
.content {
  margin: 75px 0 0 275px;
  width: calc(100vw - 303px);
}
</style>
