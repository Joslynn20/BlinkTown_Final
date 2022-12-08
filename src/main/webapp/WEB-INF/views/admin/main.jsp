<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/adminMain.css">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.0/Chart.js"></script>
</head>
<body>
<div class="tabs-wrap">
	<ul class="tabs">
	  <li class="active" rel="tab1">Users</li>
	  <li rel="tab2">Shop</li>
	  <li rel="tab3">Order</li>
	  <li rel="tab4">Chart</li>
	</ul>
	<div class="tab_container">
	  <h3 class="d_active tab_drawer_heading" rel="tab1">Tab 1</h3>
	  <div id="tab1" class="tab_content">
		<div class="users_chart">
					<div class="card">
						<!-- Custom information -->
						<div class="about">
							<h3>Member</h3>
							<p class="lead">Membership Trend</p>
						</div>

						<!-- Canvas for Chart.js -->
						<canvas id="canvas" height="200"></canvas>

						<!-- Custom tooltip canvas -->
						<canvas id="tooltip-canvas" width="150" height="100"></canvas>

					</div>
					<div class="users_chart02">
						<div class="user-tab">

							<ul class="user-tabs ">
								<li ><a href="#" >All Member</a></li>
								<li><a href="#">Free Member</a></li>
								<li><a href="#">Registered Member</a></li>
							</ul>
							<!-- / tabs -->

							<div class="user-tab_content">

								<div class="user-tabs_item">
									<div class="tbl-header">
										<table cellpadding="0" cellspacing="0" border="0">
											<thead>
												<tr>
													<th style="width: 30%;">ID</th>
													<th style="width: 13%;">Phone</th>
													<th style="width: 20%;">E-mail</th>
													<th style="width: 15%;">Nickname</th>
													<th style="width: 4%;">M</th>
													<th style="width: 13%;">JoinDate</th>
												</tr>
											</thead>
										</table>
									</div>
									<div class="tbl-content">
										<table cellpadding="0" cellspacing="0" border="0">
											<tbody>
												<tr>
													<td style="width: 30%;">sydy8995@gmail.com_abcdefghgfgfdsfsd</td>
													<td style="width: 13%;">010-0000-000</td>
													<td style="width: 20%;">comzaxxer@naver.com</td>
													<td style="width: 15%;">springboot</td>
													<td style="width: 4%;">N</td>
													<td style="width: 13%;">2022-01-05</td>
												</tr>
												<tr>
													<td style="width: 30%;">sydy8995@gmail.com_abcdefghgfgfdsfsd</td>
													<td style="width: 13%;">010-0000-000</td>
													<td style="width: 20%;">comzaxxer@naver.com</td>
													<td style="width: 15%;">springboot</td>
													<td style="width: 4%;">Y</td>
													<td style="width: 13%;">2022-01-05</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<!-- / tabs_item -->

								<div class="user-tabs_item">
									<div class="tbl-header">
										<table cellpadding="0" cellspacing="0" border="0">
											<thead>
												<tr>
													<th style="width: 30%;">ID</th>
													<th style="width: 13%;">Phone</th>
													<th style="width: 20%;">E-mail</th>
													<th style="width: 15%;">Nickname</th>
													<th style="width: 4%;">M</th>
													<th style="width: 13%;">JoinDate</th>
												</tr>
											</thead>
										</table>
									</div>
									<div class="tbl-content">
										<table cellpadding="0" cellspacing="0" border="0">
											<tbody>
												<tr>
													<td style="width: 30%;">sydy8995@gmail.com_abcdefghgfgfdsfsd</td>
													<td style="width: 13%;">010-0000-000</td>
													<td style="width: 20%;">comzaxxer@naver.com</td>
													<td style="width: 15%;">springboot</td>
													<td style="width: 4%;">N</td>
													<td style="width: 13%;">2022-01-05</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<!-- / tabs_item -->

								<div class="user-tabs_item">
									<div class="tbl-header">
										<table cellpadding="0" cellspacing="0" border="0">
											<thead>
												<tr>
													<th style="width: 30%;">ID</th>
													<th style="width: 13%;">Phone</th>
													<th style="width: 20%;">E-mail</th>
													<th style="width: 15%;">Nickname</th>
													<th style="width: 4%;">M</th>
													<th style="width: 13%;">JoinDate</th>
												</tr>
											</thead>
										</table>
									</div>
									<div class="tbl-content">
										<table cellpadding="0" cellspacing="0" border="0">
											<tbody>
												<tr>
													<td style="width: 30%;">sydy8995@gmail.com_abcdefghgfgfdsfsd</td>
													<td style="width: 13%;">010-0000-000</td>
													<td style="width: 20%;">comzaxxer@naver.com</td>
													<td style="width: 15%;">springboot</td>
													<td style="width: 4%;">N</td>
													<td style="width: 13%;">2022-01-05</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<!-- / tabs_item -->

							</div>
							<!-- / tab_content -->
						</div>
						<!-- / tab -->



					</div>
				</div>
		
	  </div>

	  <!-- #tab1 -->



			<h3 class="tab_drawer_heading" rel="tab2">Tab 2</h3>
			<div id="tab2" class="tab_content">
			<form action="/admin/albumInsertForm">
				<button class="insert-btn" type="submit">상품등록</button>				
				<select  class="insert-select">
					<option selected="selected" value="">Category</option>
					<option value="">Album</option>
					<option value="">Goods</option>
				</select>
			</form>
			
				<div class="tbl-header" style="clear: both;">
					<table cellpadding="0" cellspacing="0" border="0">
						<thead>
							<tr>
								<th style="width: 10%;">상품코드</th>
								<th style="width: 25%;">상품명</th>
								<th style="width: 15%;">가격</th>
								<th style="width: 10%;">재고</th>
								<th style="width: 15%;">등록일</th>
								<th style="width: 15%;">삭제</th>
							</tr>
						</thead>
					</table>
				</div>
				<div class="tbl-content">
					<table cellpadding="0" cellspacing="0" border="0">
						<tbody>
							<tr>
								<td style="width: 10%;">A01</td>
								<td style="width: 25%;"><a href="/admin/albumUpdateForm">web.mvc.BlinkTownFinalApplication</a></td>
								<td style="width: 15%;">10000원</td>
								<td style="width: 10%;">12개</td>
								<td style="width: 15%;">2022-01-05</td>
								<td style="width: 15%;"><button>삭제</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- #tab2 -->


			<h3 class="tab_drawer_heading" rel="tab3">Tab 3</h3>			
			<div id="tab3" class="tab_content">
			<br><br>
			<div class="tbl-header" style="clear: both;">
					<table cellpadding="0" cellspacing="0" border="0">
						<thead>
							<tr>
								<th style="width: 12%;">주문번호</th>
								<th style="width: 13%;">주문날짜</th>
								<th style="width: 13%;">아이디</th>
								<th style="width: 12.5%;">이름</th>
								<th style="width: 12%;">전화번호</th>
								<th style="width: 37.5%;">주소지</th>
							</tr>
						</thead>
					</table>
				</div>
				<div class="tbl-content" id="ordersList">
					<table cellpadding="0" cellspacing="0" border="0">
						<tbody>
						<c:forEach items="${ordersList}" var="orders">
							<tr>
								<td style="width: 12%;">${orders.ordersNo}</td>
								<td style="width: 13.5%;">${orders.ordersDate}</td>
								<td style="width: 13%;">${orders.users.usersId}</td>
								<td style="width: 12.5%;">${orders.ordersReceiverName}</td>
								<td style="width: 12%;">${orders.ordersReceiverPhone}</td>
								<td style="width: 37%;">${orders.ordersAddr}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<!-- #tab3 -->
	  
	  
	  <h3 class="tab_drawer_heading" rel="tab4">Tab 4</h3>
	  <div id="tab4" class="tab_content">
	  
	  <!-- 전체매출조회 -->
	  <div class="Sales-check">
	  	<div>
	  		<h1 style="margin-bottom: 20px;">전체매출 : <span>000,000</span> 원</h1>
	  	</div>
	  </div>
	  <!-- 월별매출조회 -->
	  	<div class="sales-wrap">
	  		<div class="card-monthlySales">
				  <!-- Custom information -->
				  <div class="about-monthlySales">
				    <h3>월별매출조회</h3>
				    <p class="lead-monthlySales">Monthly Sales Trend</p>
				  </div>
				  
				  <!-- Canvas for Chart.js -->
				  <canvas id="canvas-monthlySales"></canvas>
				  
				  <!-- Custom Axis -->
				  <div class="axis-monthlySales">
				    <div class="tick-monthlySales">
				      <span class="day-number-monthlySales">1</span>
				      <span class="day-name-monthlySales">Jan</span>
				      <span class="value-monthlySales value--this-monthlySales">00장</span>
				    </div>
				    <div class="tick-monthlySales">
				      <span class="day-number-monthlySales">2</span>
				      <span class="day-name-monthlySales">Feb</span>
				      <span class="value-monthlySales value--this-monthlySales">00장</span>
				    </div>
				    <div class="tick-monthlySales">
				      <span class="day-number-monthlySales">3</span>
				      <span class="day-name-monthlySales">Mar</span>
				      <span class="value-monthlySales value--this-monthlySales">00장</span>
				    </div>
				    <div class="tick-monthlySales">
				      <span class="day-number-monthlySales">4</span>
				      <span class="day-name-monthlySales">Apr</span>
				      <span class="value-monthlySales value--this-monthlySales">00장</span>
				    </div>
				    <div class="tick-monthlySales">
				      <span class="day-number-monthlySales">5</span>
				      <span class="day-name-monthlySales">May</span>
				      <span class="value-monthlySales value--this-monthlySales">00장</span>
				    </div>
				    <div class="tick-monthlySales">
				      <span class="day-number-monthlySales">6</span>
				      <span class="day-name-monthlySales">Jun</span>
				      <span class="value-monthlySales value--this-monthlySales">00장</span>
				    </div>
				    <div class="tick-monthlySales">
				      <span class="day-number-monthlySales">7</span>
				      <span class="day-name-monthlySales">Jul</span>
				      <span class="value-monthlySales value--this-monthlySales">00장</span>
				    </div>
				    <div class="tick-monthlySales">
				      <span class="day-number-monthlySales">8</span>
				      <span class="day-name-monthlySales">Aug</span>
				      <span class="value-monthlySales value--this-monthlySales">00장</span>
				    </div>
				    <div class="tick-monthlySales">
				      <span class="day-number-monthlySales">9</span>
				      <span class="day-name-monthlySales">Sep</span>
				      <span class="value-monthlySales value--this-monthlySales">00장</span>
				    </div>
				    <div class="tick-monthlySales">
				      <span class="day-number-monthlySales">10</span>
				      <span class="day-name-monthlySales">Oct</span>
				      <span class="value-monthlySales value--this-monthlySales">00장</span>
				    </div>
				    <div class="tick-monthlySales">
				      <span class="day-number-monthlySales">11</span>
				      <span class="day-name-monthlySales">Nov</span>
				      <span class="value-monthlySales value--this-monthlySales">00장</span>
				    </div>
				    <div class="tick-monthlySales">
				      <span class="day-number-monthlySales">12</span>
				      <span class="day-name-monthlySales">Dec</span>
				      <span class="value-monthlySales value--this-monthlySales">00장</span>
				    </div>
				  </div>
				</div>	  	
	  	<!-- 월별매출끝 -->
	  		
	  		
	  	<!-- 앨범별매출 -->
	  	<div class="card-monthlySales card-AlbumSales">
	  	<!-- Custom information -->
				  <div class="about-monthlySales" style="height: 0px;">
				    <h3>앨범매출조회</h3>
				    <p class="lead-monthlySales">Album Sales Trend</p>
				  </div>
				  
				  <div class="canvas-AlbumSales-wrap">
				  <!-- Canvas for Chart.js -->
				  <canvas id="canvas-AlbumSales"></canvas>
				  </div>
	  	</div>
	  	<!-- 앨범별매출 -->
	  </div>
	  <!-- #tab4 --> 
	  
	  </div>
	</div>
	<!-- .tab_container -->
</div>
<script type="text/javascript">
//tabbed content
// http://www.entheosweb.com/tutorials/css/tabs.asp
$(".tab_content").hide();
$(".tab_content:first").show();

/* if in tab mode */
$("ul.tabs li").click(function() {
	
  $(".tab_content").hide();
  var activeTab = $(this).attr("rel"); 
  $("#"+activeTab).fadeIn();		
	
  $("ul.tabs li").removeClass("active");
  $(this).addClass("active");

  $(".tab_drawer_heading").removeClass("d_active");
  $(".tab_drawer_heading[rel^='"+activeTab+"']").addClass("d_active");
  
});
/* if in drawer mode */
$(".tab_drawer_heading").click(function() {
  
  $(".tab_content").hide();
  var d_activeTab = $(this).attr("rel"); 
  $("#"+d_activeTab).fadeIn();
  
  $(".tab_drawer_heading").removeClass("d_active");
  $(this).addClass("d_active");
  
  $("ul.tabs li").removeClass("active");
  $("ul.tabs li[rel^='"+d_activeTab+"']").addClass("active");
});


/* Extra class "tab_last" 
   to add border to right side
   of last tab */
$('ul.tabs li').last().addClass("tab_last");

</script>
<script type="text/javascript">
var canvas = document.getElementById("canvas");
var tooltipCanvas = document.getElementById("tooltip-canvas");

var gradientBlue = canvas.getContext('2d').createLinearGradient(0, 0, 0, 150);
gradientBlue.addColorStop(0, '#5555FF');
gradientBlue.addColorStop(1, '#9787FF');

var gradientRed = canvas.getContext('2d').createLinearGradient(0, 0, 0, 150);
gradientRed.addColorStop(0, '#FF55B8');
gradientRed.addColorStop(1, '#FF8787');

var gradientGrey = canvas.getContext('2d').createLinearGradient(0, 0, 0, 150);
gradientGrey.addColorStop(0, '#888888');
gradientGrey.addColorStop(1, '#AAAAAA');

window.arcSpacing = 0.15;
window.segmentHovered = false;

function textInCenter(value, label) {
  var ctx = tooltipCanvas.getContext('2d');
  ctx.clearRect(0, 0, tooltipCanvas.width, tooltipCanvas.height)
  
	ctx.restore();
    
  // Draw value
  ctx.fillStyle = '#333333';
  ctx.font = '24px sans-serif';
  ctx.textBaseline = 'middle';

  // Define text position
  var textPosition = {
    x: Math.round((tooltipCanvas.width - ctx.measureText(value).width) / 2),
    y: tooltipCanvas.height / 2,
  };

  ctx.fillText(value, textPosition.x, textPosition.y);

  // Draw label
  ctx.fillStyle = '#AAAAAA';
  ctx.font = '8px sans-serif';

  // Define text position
  var labelTextPosition = {
    x: Math.round((tooltipCanvas.width - ctx.measureText(label).width) / 2),
    y: tooltipCanvas.height / 2,
  };

  ctx.fillText(label, labelTextPosition.x, labelTextPosition.y - 20);
  ctx.save();
}

Chart.elements.Arc.prototype.draw = function() {
  var ctx = this._chart.ctx;
  var vm = this._view;
  var sA = vm.startAngle;
  var eA = vm.endAngle;

  ctx.beginPath();
  ctx.arc(vm.x, vm.y, vm.outerRadius, sA + window.arcSpacing, eA - window.arcSpacing);
  ctx.strokeStyle = vm.backgroundColor;
  ctx.lineWidth = vm.borderWidth;
  ctx.lineCap = 'round';
  ctx.stroke();
  ctx.closePath();
};

var config = {
    type: 'doughnut',
    data: {
        labels: ['Registered Member' , 'Free Member'],
        datasets: [
          {
              data: [1221, 1212],
              backgroundColor: [
              	gradientRed,
                gradientBlue,
              ],
          }
        ]
    },
    options: {
    		cutoutPercentage: 80,
    		elements: {
        	arc: {
          	borderWidth: 12,
          },
        },
        legend: {
        	display: false,
        },
        animation: {
        	onComplete: function(animation) {
          	if (!window.segmentHovered) {
              var value = this.config.data.datasets[0].data.reduce(function(a, b) { 
                return a + b;
              }, 0);
              var label = 'T O T A L';

              textInCenter(value, label);
            }
          },
        },
        tooltips: {
        	enabled: false,
        	custom: function(tooltip) {
          	if (tooltip.body) {
              var line = tooltip.body[0].lines[0],
              	parts = line.split(': ');
              textInCenter(parts[1], parts[0].split('').join(' ').toUpperCase());
              window.segmentHovered = true;
            } else {
            	window.segmentHovered = false;
            }
          },
        },
    },
};

window.chart = new Chart(canvas, config);


</script>

<script type="text/javascript">
$(document).ready(function() { 

	(function ($) { 
		$('.user-tab ul.user-tabs').addClass('active').find('> li:eq(0)').addClass('current');
		
		$('.user-tab ul.user-tabs li a').click(function (g) { 
			var tab = $(this).closest('.user-tab'), 
				index = $(this).closest('li').index();
			
			tab.find('ul.user-tabs > li').removeClass('current');
			$(this).closest('li').addClass('current');
			
			tab.find('.user-tab_content').find('div.user-tabs_item').not('div.user-tabs_item:eq(' + index + ')').slideUp();
			tab.find('.user-tab_content').find('div.user-tabs_item:eq(' + index + ')').slideDown();
			
			g.preventDefault();
		} );
	})(jQuery);

});
</script>

<!-- 월별매출 -->
<script type="text/javascript">
var canvas = document.getElementById("canvas-monthlySales");

//Apply multiply blend when drawing datasets
var multiply = {
beforeDatasetsDraw: function(chart, options, el) {
 chart.ctx.globalCompositeOperation = 'multiply';
},
afterDatasetsDraw: function(chart, options) {
 chart.ctx.globalCompositeOperation = 'source-over';
},
};

//Gradient color - this week
var gradientThisWeek = canvas.getContext('2d').createLinearGradient(0, 0, 0, 150);
gradientThisWeek.addColorStop(0, '#5555FF');
gradientThisWeek.addColorStop(1, '#9787FF');

//Gradient color - previous week
var gradientPrevWeek = canvas.getContext('2d').createLinearGradient(0, 0, 0, 150);
gradientPrevWeek.addColorStop(0, '#FF55B8');
gradientPrevWeek.addColorStop(1, '#FF8787');

var config = {
 type: 'line',
 data: {
     labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep","Oct","Nov","Dec"],
     datasets: [
       {
           label: 'Temperature',
           data: [13213, 13213, 13213, 13213, 13213, 13213, 13213, 13213, 15213, 13213, 12213 ,14213],
           fill: false,
           borderColor: 'rgba(255, 255, 255, 0.2)',
           borderWidth: 2,
           pointBackgroundColor: 'transparent',
           pointBorderColor: '#FFFFFF',
           pointBorderWidth: 3,
           pointHoverBorderColor: 'rgba(255, 255, 255, 0.2)',
           pointHoverBorderWidth: 10,
           lineTension: 0,
       }
     ]
 },
 options: {
 	responsive: false,
   elements: { 
     point: {
       radius: 6,
       hitRadius: 6, 
       hoverRadius: 6 
     } 
   },
   legend: {
     display: false,
   },
   tooltips: {
   	backgroundColor: 'transparent',
     displayColors: false,
     bodyFontSize: 14,
     callbacks: {
       label: function(tooltipItems, data) { 
         return tooltipItems.yLabel + '장';
       }
     }
   },
   scales: {
     xAxes: [{
       display: false,
     }],
     yAxes: [{
       display: false,
       ticks: {
         beginAtZero: true,
       },
     }]
   }
 },
 plugins: [multiply],
};

window.chart = new Chart(canvas, config);
</script>

<!-- 앨범별매출 -->
<script type="text/javascript">
//bar chart
var bar = document.getElementById('canvas-AlbumSales');
bar.height = 400
var barConfig = new Chart(bar, {
    type: 'horizontalBar',
    data: {
        labels: ['BornPink', 'THEALBUM', 'KillThisLove', 'SquareUp'],
        datasets: [{
            label: '# of data',
            data: [30, 25, 20, 15],
            backgroundColor: ['#ffffff80','#ffffff80','#ffffff80','#ffffff80','#ffffff80'],
            borderWidth: 1
        }]
    },
    options: {
    	
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: false
                }
            }]
        },
        legend: {
            display: false,
          },
        responsive: true, // Instruct chart js to respond nicely.
        maintainAspectRatio: false, // Add to prevent default behaviour of full-width/height 
    }
})
        </script>

</body>
</html>