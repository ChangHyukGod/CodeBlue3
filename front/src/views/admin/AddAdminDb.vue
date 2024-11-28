<template>
    <div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="adminTitle" placeholder="adminTitle"
                v-model="admin.adminTitle" />
            <label for="adminTitle">adminTitle</label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="adminContent" placeholder="adminContent"
                v-model="admin.adminContent" />
            <label for="adminContent">adminContent</label>
        </div>
        <!-- TODO: 파일선택상자 -->
        <div class="input-group">
            <input type="admin" class="form-control" ref="admin" @change="select" />
            <button class="btn btn-outline-secondary" type="button" @click="save">
                Button
            </button>
        </div>
    </div>
</template>

<script>
import AdminService from '@/service/admin/AdminService';
export default {
    data() {
        return {
            admin: {
                adminTitle: "",
                adminContent: "",
                adminUrl: "", // 다운로드 url
                image: undefined, // 선택이미지
            },
        };
    },
    methods: {
        select() {
            this.admin.image = this.$refs.admin.admins[0];
        },
        async save() {
            try {
                let response = await AdminService.insert(this.admin);
                console.log(response.data); // 디버깅
                // insert 성공 -> 전체조회 강제 이동
                this.$router.push("/admin");
            } catch (error) {
                this.admin.image = undefined;
                console.log(error);
            }
        }
    },
};
</script>
<style></style>
