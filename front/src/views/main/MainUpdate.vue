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

    <!-- TODO: 현재 이미지 보기 -->
    <div class="mb-3">
      <img :src="tour.tourFileUrl" alt="이미지" class="img-fluid rounded" style="max-height: 400px; object-fit: cover"/>
    </div>

    <!-- 파일선택상자 -->
    <div class="input-group">
      <input type="file" class="form-control" ref="file" @change="select" />
    </div>
    <br/>

    <div class="mt-3" style="display:flex; justify-content: space-between;">
      <button class="btn btn-warning" type="button" @click="update">수정</button>
      <button class="btn btn-danger" type="button" @click="remove">삭제</button>
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
            price:"",
            tourFileUrl: "",
            image: undefined
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
    select() {
      const file = this.$refs.file.files[0];
      if(file){
          this.tour.image = file;
          // 이미지 URL을 생성하여 tourFileUrl에 할당
          this.tour.tourFileUrl = URL.createObjectURL(file);
      }
    },
    async update() {
      try {
        let response = await MainService.update(
          this.tour.tourId,
          this.tour
        );
        console.log(response.data);
        alert("수정되었습니다.");
      } catch (error) {
        this.tour.image = undefined;
        console.log(error);
      }
    },
    async remove() {
      try {
        let response = await MainService.remove(this.tour.tourId);
        console.log(response.data);
        this.$router.push("/");
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