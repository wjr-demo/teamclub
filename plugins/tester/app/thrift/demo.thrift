namespace java thrift

enum RequestType {
	SAY_HELLO, //问好
	QUERY_TIME //询问时间
}

struct Request {
	1: required RequestType type;
	2: required string name;
	3: optional i32 age;
}

exception RequestException {
	1: required i32 code;
	2: optional string reason;
}

service HelloWordService {
	string doAction(1: Request request ) throws (1: RequestException e)
}