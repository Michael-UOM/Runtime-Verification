package aspects;

import main.RunnerTwo;

import larva.*;
public aspect _asp_login0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_login0.initialize();
}
}
before () : (call(* *.viewAlerts(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_login0.lock){

_cls_login0 _cls_inst = _cls_login0._get_cls_login0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 22/*viewAlerts*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 22/*viewAlerts*/);
}
}
before () : (call(* *.goodLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_login0.lock){

_cls_login0 _cls_inst = _cls_login0._get_cls_login0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 20/*goodLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 20/*goodLogin*/);
}
}
before () : (call(* *.badLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_login0.lock){

_cls_login0 _cls_inst = _cls_login0._get_cls_login0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 16/*badLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 16/*badLogin*/);
}
}
before () : (call(* *.logOut(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_login0.lock){

_cls_login0 _cls_inst = _cls_login0._get_cls_login0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 18/*logOut*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 18/*logOut*/);
}
}
}