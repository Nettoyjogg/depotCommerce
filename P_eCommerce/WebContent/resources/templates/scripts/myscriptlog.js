$(function() {
	var textfield = $("input[name=conMail]");
	$('button[type="submit"]')
			.click(
					function(e) {
						e.preventDefault();
						// little validation just to check username
						if (textfield.val() != "") {
							// $("body").scrollTo("#output");
							$("#output")
									.addClass(
											"alert alert-success animated fadeInUp")
									.html(
											"Bienvenue "
													+ "<span style='text-transform:uppercase'>"
													+ textfield.val()
													+ "</span>");
							$("#output").removeClass(' alert-danger');
							// show avatar
							$(".avatar")
									.css(
											{
												"background-image" : "url('https://image.noelshack.com/fichiers/2019/10/1/1551735032-avatar.jpg')"
											});
							$("input").css({
								"height" : "0",
								"padding" : "0",
								"margin" : "0",
								"opacity" : "0"
									
							});
							// change button text
							$('button[type="submit"]').html("continue")
									.removeClass("btn-info").addClass(
											"btn-default").click(
											function() {
												document.getElementById(
														"myForm").submit();
											

											});

							
						} else {
							// remove success mesage replaced with error message
							$("#output").removeClass(' alert alert-success');
							$("#output").addClass(
									"alert alert-danger animated fadeInUp")
									.html("Rentrez un utilisateur");
						}
						// console.log(textfield.val());

					});
});