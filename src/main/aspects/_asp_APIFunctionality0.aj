package aspects;

import main.Runner;

import larva.*;
public aspect _asp_APIFunctionality0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_APIFunctionality0.initialize();
}
}
before () : (call(* *.uploadCorrectAlert(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_APIFunctionality0.lock){

_cls_APIFunctionality0 _cls_inst = _cls_APIFunctionality0._get_cls_APIFunctionality0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 24/*uploadCorrectAlert*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 24/*uploadCorrectAlert*/);
}
}
before () : (call(* *.uploadIncorrectAlert(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_APIFunctionality0.lock){

_cls_APIFunctionality0 _cls_inst = _cls_APIFunctionality0._get_cls_APIFunctionality0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 26/*uploadIncorrectAlert*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 26/*uploadIncorrectAlert*/);
}
}
before () : (call(* *.purgeAlerts(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_APIFunctionality0.lock){

_cls_APIFunctionality0 _cls_inst = _cls_APIFunctionality0._get_cls_APIFunctionality0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 28/*purgeAlerts*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 28/*purgeAlerts*/);
}
}
}