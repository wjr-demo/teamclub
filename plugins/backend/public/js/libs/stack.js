define([], function(){
    function Stack() {
        this.top = null;
        this.size = 0;
    };
    Stack.prototype = {
        constructor: Stack,
        push: function (data) {
            var node = {
                data: data,
                next: null
            };

            node.next = this.top;
            this.top = node;
            this.size++;
        },
        peek: function () {
            return this.top === null ?
                null :
                this.top.data;
        },
        pop: function () {
            if (this.top === null) return null;

            var out = this.top;
            this.top = this.top.next;

            if (this.size > 0) this.size--;

            return out.data;
        },
        clear: function () {
            this.top = null;
            this.size = 0;
        },
        displayAll: function () {
            if (this.top === null) return null;

            var arr = [];
            var current = this.top;

            for (var i = 0, len = this.size; i < len; i++) {
                arr[i] = current.data;
                current = current.next;
            }

            return arr;
        }
    };
    return Stack;
});
