<!-- including bootstrap libraries -->
<script src="/js/jquery/jquery.js"></script>
<script src="/assets/bootstrap/js/bootstrap.js"></script>
<script src="/assets/bootstrap/js/bootstrap.bundle.js"></script>

<link rel="stylesheet" href="/assets/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="/assets/bootstrap/css/bootstrap-grid.css">
<link rel="stylesheet" href="/assets/bootstrap/css/bootstrap-reboot.css">

<script rel="text/javascript">
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};
</script>