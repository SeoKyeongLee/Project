// 스크롤시 네비게이션 바 투명도 증가
const menu = document.querySelector('#menu');
const menuHeight = menu.getBoundingClientRect().height;
document.addEventListener('scroll', () => {
  if (window.scrollY > menuHeight) {
    document.querySelector("nav").style.background="rgb(255, 255, 255, 0.8)";
  } else {
    document.querySelector("nav").style.background="white";
  }
});
//effect 클래스에 화면 스크롤에 따른 fadeInUp 클래스 적용
$(document).ready(function() {
  $(window).scroll(function() {
    $('.effect').each(function(i) {
      var bottom_of_element = $(this).offset().top + $(this).outerHeight() / 3;
      var bottom_of_window = $(window).scrollTop() + $(window).height();
    
      if (bottom_of_window > bottom_of_element && !$(this).hasClass('fadeInUp')) {
        $(this).addClass('fadeInUp');
      }
    });
  });
});
//캐러셀 설정
$(document).ready(function() {
  $('.carousel').carousel({
    interval: 3000, // 각 슬라이드 간의 시간 간격
    pause: false // 마우스 오버 시 일시 정지 하지 않음
  });
});