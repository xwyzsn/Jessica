import {date} from "quasar";
function getDiff(start,unit){

  let now = new Date();
  start = date.addToDate(start,{month:-1});
  if(unit==="day"){
    return date.getDateDiff(now,start);
  }
  else if(unit==="year"){

    let totalDay = date.getDateDiff(now,start,'days');
    let year =  date.getDateDiff(now,start,"years");
    let remain = totalDay -year*365;
    let month = parseInt(remain/30);
    let day = remain -month*30;
    return [year,month,day];
  }

}
export {getDiff}
