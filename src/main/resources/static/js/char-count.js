
/*To count letters in textarea*/
function countChar(val, maxchar) {
        var len = val.value.length;
        if (len >= maxchar) {
          val.value = val.value.substring(0, maxchar);
        } else {
          $('#charNumLeft').text(maxchar - len);
        }
        $('#charNum').text(len);
      };