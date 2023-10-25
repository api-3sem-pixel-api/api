<template>
    <div class="row">
        <div class="col-6">
            <label class="form-control">Inicio do Mes</label>
            <input type="datetime" class="form-control">
        </div>
        <div class="col-6">
            <label class="form-control">Fechamento do Mes</label>
            <input type="datetime" class="form-control">
        </div>
        <div class="col-4">
            <label class="form-control">Inicio da hora Noturna</label>
            <input type="time" class="form-control">
        </div>
        <div class="col-4">
            <label class="form-control">Fim da hora Noturna</label>
            <input type="time" class="form-control">
        </div>
        <div class="col-3">
            <button type="button" class="btn btn-outline-primary">
                Salvar
            </button>
        </div>
    </div>
</template>
  
<script lang="ts">
import { Options, Vue } from 'vue-class-component';

@Options({
    components: {
    }
})
export default class ParametrizacaoView extends Vue {
};
</script>
  
<style scoped>
</style>