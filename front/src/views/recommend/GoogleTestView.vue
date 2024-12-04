<template>
    <div class="container">
      <h1 class="my-4">주변 추천 장소</h1>
      <div id="map" class="mb-4" style="height: 400px;"></div>
      <div class="row">
        <div v-for="place in places" :key="place.place_id" class="col-md-4 mb-4">
          <div class="card h-100 shadow-sm">
            <div class="card-body">
              <h5 class="card-title">{{ place.name }}</h5>
              <p class="card-text">
                <i class="bi bi-geo-alt me-2"></i>{{ place.vicinity }}
              </p>
  
              <!-- 장소 세부 정보 -->
              <div class="d-flex justify-content-between align-items-center">
                <!-- Google Maps 링크 -->
                <a 
                  :href="`https://www.google.com/maps/search/?api=1&query=${encodeURIComponent(place.name)}&query_place_id=${place.place_id}`" 
                  target="_blank" 
                  class="btn btn-primary btn-sm"
                >
                  지도에서 보기
                </a>
                
                <!-- 평점 표시 (있는 경우) -->
                <div v-if="place.rating" class="text-warning">
                  <i class="bi bi-star-fill"></i> {{ place.rating }}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  /* global google */
  export default {
    data() {
      return {
        map: null,
        service: null,
        places: [],
      };
    },
    mounted() {
      // Google Maps API 스크립트 로드 확인
      if (window.google && window.google.maps) {
        this.initMap();
      } else {
        // 스크립트가 로드되지 않았다면 대기
        window.addEventListener('load', this.initMap);
      }
    },
    methods: {
      initMap() {
        try {
          // 지도 설정
          const mapOptions = {
            center: new google.maps.LatLng(37.7749, -122.4194), // 샌프란시스코 좌표
            zoom: 13,
          };
  
          // 지도 초기화
          this.map = new google.maps.Map(document.getElementById('map'), mapOptions);
  
          // 장소 서비스 초기화
          this.service = new google.maps.places.PlacesService(this.map);
  
          // 근처 장소 검색
          this.searchNearbyPlaces();
        } catch (error) {
          console.error('Google Maps 초기화 중 오류:', error);
        }
      },
      searchNearbyPlaces() {
        const request = {
          location: this.map.getCenter(),
          radius: 1000, // 반경 1km 내의 장소 검색
          type: ['restaurant'], // 레스토랑 검색
        };
  
        // 장소 검색 실행
        this.service.nearbySearch(request, (results, status) => {
          if (status === google.maps.places.PlacesServiceStatus.OK) {
            this.places = results; // 결과를 places 배열에 저장
            console.log('검색된 장소:', results);
          } else {
            console.error('Places API 요청 실패:', status);
          }
        });
      }
    },
  };
  </script>
  
  <style scoped>
  #map {
    width: 100%;
    height: 400px;
  }
  </style>
  