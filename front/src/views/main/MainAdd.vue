<template>
<div>
    <!-- name -->
    <div class="form-floating mb-3">
        <input type="text" class="form-control" id="name" placeholder="name" v-model="main.name" />
        <label for="dname">name</label>
    </div>

    <!-- location -->
    <div class="form-floating mb-3">
        <input type="text" class="form-control" id="location" placeholder="location" v-model="main.location" />
        <label for="loc">location</label>
    </div>

    <!-- description -->
    <div class="form-floating mb-3">
        <input type="text" class="form-control" id="description" placeholder="description" v-model="main.description" />
        <label for="loc">description</label>
    </div>

    <!-- price -->
    <div class="form-floating mb-3">
        <input type="text" class="form-control" id="price" placeholder="price" v-model="main.price" />
        <label for="loc">price</label>
    </div>

    <!-- image -->
    <div class="input-group">
      <input type="file" class="form-control" ref="file" @change="select"/>
      <button class="btn btn-outline-secondary" type="button" @click="save">저장</button>
    </div>
</div>
</template>
<script>
import MainService from '@/services/main/MainService';
export default {
    data() {
        return {
            main:{
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
        select(){
            this.main.image = this.$refs.file.files[0];
        },
        async save(){
            try {
            let response = await MainService.insert(this.main);
            console.log(response.data);
            // TODO : 저장후 강제이동 : 전체조회 페이지(/dept)
            this.$router.push("/")
            } catch (error) {
                this.main.image = undefined;
                console.log(error);
            }
        }
    },
}
</script>
<style></style>