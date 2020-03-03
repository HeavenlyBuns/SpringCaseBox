package feat.common;

/**
 * @author user
 * @title: ApiResult
 * @projectName my_git
 * @description: response 结果集
 * @date 2019/4/29 15:35
 */
public class ApiResult<T> {

    private State state;
    private Data<T> data;


    private ApiResult(State state, Data<T> data) {
        this.state = state;
        this.data = data;
    }

    public ApiResult(Builder tBuilder) {
        this.state = tBuilder.state;
        this.data = tBuilder.data;

    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Data<T> getData() {
        return data;
    }

    public void setData(Data<T> data) {
        this.data = data;
    }

    public static class Builder<T> {
        private State state;
        private Data<T> data;

        public Builder(Integer code, String desc) {
            this.state = new State(code, desc);

        }

        Builder<T> data(T data) {
            this.data = new Data<>(data);
            return this;

        }

        public ApiResult builder() {
            return new ApiResult(this);
        }
    }

    public static class State {
        private Integer code;
        private String desc;

        public State(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "State{" +
                    "code=" + code +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }

    public static class Data<T> {

        T t;

        public Data(T t) {
            this.t = t;
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "t=" + t +
                    '}';
        }
    }


}
