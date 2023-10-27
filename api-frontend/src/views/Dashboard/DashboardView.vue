<template>
    <div class="row">
        <div class="col-4">
            <label>Cr</label>
            <input type="text" class="form-control">
        </div>
        <div class="col-4">
            <label>Cliente</label>
            <input type="text" class="form-control">
        </div>
        <div class="col-4">
            <label >Inicio</label>
            <input type="date" class="form-control">
        </div>
    </div>
    <div class="row mt-2">
        <div class="col-4">
            <label>Fim</label>
            <input  type="date" class="form-control">
        </div>
        <div class="col-5 mt-4">
            <button type="button" v-on:click="save()" class="btn btn-outline-primary">
                Carregar Dashboard
            </button>
        </div>
    </div>
    <div class="row mt-5">
        <div id="col-12">
            <apexchart 
                type="bar" 
                height="350" 
                :options="chartConfig.chartOptions" 
                :series="chartConfig.series"
            ></apexchart>
        </div>
    </div>
</template>
  
<script lang="ts">
import { Options, Vue } from 'vue-class-component';

export default class DashboardView extends Vue {
    chartConfig = {
        series: [
            {
                data: [400, 430, 448, 470]
            }
        ],
        chartOptions: {
            chart: {
                type: 'bar',
                height: 100
            },
            plotOptions: {
                bar: {
                    borderRadius: 6,
                    horizontal: true,
                }
            },
            dataLabels: {
                enabled: false
            },
            xaxis: {
                categories: ['Horas Extra - Diario', 'Horas Extra - Noturno', 'Sobreaviso - Diario', 'Sobreaviso - Noturno'],
            }
        },
    }    
    
    created(): void {
        this.getData();
    }

    getData(){
    }
};
</script>
  
<style scoped></style>