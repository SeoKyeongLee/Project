//
//// 스크롤시 네비게이션 바 투명도 증가
const menu = document.querySelector('#menu');
const menuHeight = menu.getBoundingClientRect().height;
document.addEventListener('scroll', () => {
    if (window.scrollY > menuHeight) {
        document.querySelector("nav").style.background = "rgb(255, 255, 255, 0.8)";
    } else {
        document.querySelector("nav").style.background = "white";
    }
});

$(function() {
	// OECD 바 차트 js
	const ctx = document.getElementById('myChart_contries');

	new Chart(ctx, {
		type : 'bar',
		data : {
			labels : [ '멕시코', '한국', '일본', '미국', '캐나다', '프랑스', '영국', '벨기에',
					'헝가리', '뉴질랜드', 'OECD평균', '네덜란드', '호주', '이탈리아', '덴마크',
					'스위스', '독일', '노르웨이', '오스트리아' ],
			datasets : [ {
				label : ' OECD 국가 임상 의사 수 (단위 : 명/인구 1,000명)',
				data : [ 2.5, 2.6, 2.6, 2.7, 2.8, 3.2, 3.2, 3.3, 3.3, 3.5, 3.7,
						3.9, 4.0, 4.1, 4.4, 4.4, 4.5, 5.2, 5.4 ],
				borderWidth : 1,
				borderRadius : 5
			} ]
		},
		options : {
			responsive : true,
			scales : {
				y : {
					beginAtZero : true
				}
			}
		}
	});
});
$(function() {

	var ctx1 = document.getElementById('myChart1_doctor');
	 var ctxChart1 = new Chart(
			ctx1,
			{

				// The type of chart we want to create
				type : 'bar',

				// The data for our dataset
				data : {
					labels : [ '서울', '광주', '대전', '부산', '대구', '전북', '인천', '강원',
							'제주', '경기', '전남', '경남', '울산', '충북', '충남', '경북' ],
					datasets : [
							{
								label : '인구 1,000명당 평균 의사 수',
								type : 'line',
								fill : false,
								yAxisID : 'B',
								borderRadius : 30,
								lineTension : 0.2,
								backgroundColor : '#1c3a66',
								borderColor : '#1c3a66',
								data : [ 3.0, 2.4, 2.3, 2.3, 2.0, 1.7, 1.7,
										1.7, 1.6, 1.6, 1.6, 1.5, 1.5, 1.4, 1.3, 1.3 ]
							},
							{
								label : '인구 1,000명당 부족 의사 수 (2.3명 기준)',
								type : 'bar',
								yAxisID : 'A',
								yAxes : 'right',
								backgroundColor : '#4F98FF',
								borderColor : '#4F98FF',
								borderRadius : 10,
								data : [ 0, 0, 0, 0, 0, 547, 1774, 925, 402,
										9253, 1306, 2355, 920, 2355, 2215, 2666 ]

							} ]
				},

				// Configuration options
				options : {
					responsive : true,
					legend : {
						labels : {
							fontColor : 'white' // label color
						},
						responsive : false,
						tooltips : {
							enabled : false
						},
						hover : {
							animationDuration : 0
						},
						animation : {
							duration : 1,
							onComplete : function() {
								let chartInstance = this.chart, ctx = chartInstance.ctx;
								ctx.font = Chart.helpers
										.fontString(
												Chart.defaults.global.defaultFontSize,
												Chart.defaults.global.defaultFontStyle,
												Chart.defaults.global.defaultFontFamily);
								ctx.fillStyle = 'purple';
								ctx.textAlign = 'center';
								ctx.textBaseline = 'bottom';

								this.data.datasets
										.forEach(function(dataset, i) {
											let meta = chartInstance.controller
													.getDatasetMeta(i);
											meta.data.forEach(function(bar,
													index) {
												let data = dataset.data[index];
												ctx.fillText(data,
														bar._model.x,
														bar._model.y - 5);
											});
										});
							}
						}
					},
					scales : {

						// First y-axis
						yAxes : [ {
							id : 'A',
							position : 'left',
							stacked : true,
							ticks : {
								fontColor : 'white' // y-axis font color
							},
							scaleLabel : {
								display : true,
								labelString : '인구 1,000명당 의사 수', // Add your
								// label
								// here
								fontColor : 'white' // Label font color
							}
						},
						// Second y-axis
						{
							id : 'B',
							type : 'linear',
							display : true,
							position : 'right',
							gridLines : {
								display : false
							},
							ticks : {
								display : true,
								fontColor : 'white' // y-axis font color
							}
						} ],
						// x-axis
						xAxes : [ {
							stacked : true,
							ticks : {
								display : true,
								fontColor : 'white' // x-axis font color
							}
						} ]
					}
				}
			});
	 
});

$(function() {
	const ctx4 = document.getElementById('result1');

	new Chart(ctx4, {
		type : 'bar',
		data : {
			labels : [ '서울 응급의학과전문의 수', '서울 2PPH 충족 전문의 수', '경기 응급의학과 전문의 수',
					'경기 2PPH 충족 전문의 수' ],
			datasets : [ {
				label : '인적자원 재배치 분석 결과',
				data : [ 994, 519, 347, 688 ],
				// backgroundColor: ['#36A2EB', '#FF6384', '#36A2EB',
				// '#FF6384'],
				borderWidth : 1,
				borderRadius : 5
			} ]
		},
		options : {
			indexAxis : 'y', // 수평차트 만들기
			responsive : true,
			scales : {
				y : {
					beginAtZero : true
				}
			}
		}
	});
});


$(document).ready(function () {
    $("#button").click(function () {
        $("#panel").toggle();
    });
});