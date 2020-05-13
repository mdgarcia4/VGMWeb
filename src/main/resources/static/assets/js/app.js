
  // creating Angular Module
  var websiteApp = angular.module('websiteApp', []);
  
  // // create angular controller and pass in $scope and $http
  // websiteApp.controller('FormController',function($scope, $http,$timeout) {
// 	  
	  // // creating a blank object to hold our form information.
	  // //$scope will allow this to pass between controller and view
	  // $scope.formData = {};
	   // $scope.submitButtonDisabled = false;
	  // // submission message doesn't show when page loads
	  // $scope.submission = false;
	  // // Updated code thanks to Yotam
	  // var param = function(data) {
			// var returnString = '';
			// for (d in data){
				// if (data.hasOwnProperty(d))
				   // returnString += d + '=' + data[d] + '&';
			// }
			// // Remove last ampersand and return
			// return returnString.slice( 0, returnString.length - 1 );
	  // };
	  // $scope.submitForm = function() {
	  	// var status = true;
	  	 // $("#contact input").each(function(){
	  	 	// if(angular.element(this).hasClass("error")){
	  	 		// status = false;
	  	 	// }
	  	 // })
// 	  	 
	  	// if(status){
// 	  		
// 	  	
		// $http({
		// method : 'POST',
		// url : 'mail.php',
		// data : param($scope.formData), // pass in data as strings
		// headers : { 'Content-Type': 'application/x-www-form-urlencoded' } // set the headers so angular passing info as form data (not request payload)
	  // })
		// .success(function(data) {
		  // if (!data.success) {
// 		  	
		   // // if not successful, bind errors to error variables
		   // $scope.errorName = data.errors.name;
		   // $scope.errorEmail = data.errors.email;
		   // $scope.errorSubject = data.errors.subject;
		  // // $scope.errorTextarea = data.errors.message;
		   // $scope.submissionMessage = data.messageError;
		   // $scope.submission = true; //shows the error message
		  // } else {
		  // // if successful, bind success message to message
		   // $scope.successsubmissionMessage = data.messageSuccess;
		   // $scope.formData = {}; // form fields are emptied with this line
		   // $scope.submission = false; //shows the success message
		   // $scope.submissionMessage = "";
		   // $timeout(function(){$scope.successsubmissionMessage = ""},3000);
		  // }
		 // });
		// }
	   // };
// });



websiteApp.controller('lookcontactCtrl', function($scope, $http) {

	$scope.contactformData = {};
	$scope.submit = false;
	$scope.submitcontactForm = function(data1) {
		$scope.submit = true;
		//alert(data1);
		var name=/^[a-zA-Z]/;
  
		if (data1 == true) {
			if (!$scope.contactformData)
				return false;
		} else if (!$scope.contactformData) {

			return false;
		} 
		else if($scope.contactformData.email == undefined){
			$('#email').addClass('error')
		}
		else if(name.test($scope.contactformData.name)== false || $scope.contactformData.name ==undefined){
			 
		$('#name').addClass('error')
		}
		else {
			
			$scope.submit = false;
			$http({
				method : 'POST',
				url : 'mail.php',
				data : $.param($scope.contactformData), // pass in data as strings
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				} // set the headers so angular passing info as form data (not request payload)
			}).success(function(data) {
				if (data) {
					$scope.contactformData = {};
					$scope.submit = false;
					$('input').val('');
					$('textarea').val('')
					alert("form submit successfully");
				}
			});
		}
	};
}); 