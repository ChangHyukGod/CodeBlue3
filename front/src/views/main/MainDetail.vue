<template>
  <div>
    <!-- name -->
    <div class="form-floating mb-3">
      <input type="text" class="form-control" id="name" placeholder="name" v-model="tour.name"/>
      <label for="name">name</label>
    </div>

    <!-- location -->
    <div class="form-floating mb-3">
      <input type="text" class="form-control" id="location" placeholder="location" v-model="tour.location"/>
      <label for="location">location</label>
    </div>

    <!-- description -->
    <div class="form-floating mb-3">
      <input type="text" class="form-control" id="description" placeholder="description" v-model="tour.description"/>
      <label for="description">description</label>
    </div>

    <!-- price -->
    <div class="form-floating mb-3">
      <input type="text" class="form-control" id="price" placeholder="price" v-model="tour.price"/>
      <label for="price">price</label>
    </div>

  </div>
</template>
<script>
import MainService from '@/services/main/MainService';
export default {
  data() {
    return {
        tour:{
            tourId: "",
            name:"",
            location:"",
            description:"",
            price:""
        }
      }
    },
  methods: {
    async getDetail(tourId) {
        try {
          let response = await MainService.get(tourId);
          console.log(response.data); // 디버깅
          this.tour = response.data;
        } catch (error) {
          console.log(error);
        }
      },
  },
  mounted() {
    this.getDetail(this.$route.params.tourId);
  },
};
</script>
<style></style>