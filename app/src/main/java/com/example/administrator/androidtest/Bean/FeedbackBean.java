package com.example.administrator.androidtest.Bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/10.
 */
public class FeedbackBean {

    /**
     * code : 200
     * msg : 查询成功
     * result : [{"DataID":3,"DataText":"工地现场","OrderBy":3},{"DataID":4,"DataText":"工地检查","OrderBy":4}]
     */

    private String code;
    private String msg;
    /**
     * DataID : 3
     * DataText : 工地现场
     * OrderBy : 3
     */

    private List<ResultBean> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private int DataID;
        private String DataText;
        private int OrderBy;

        public int getDataID() {
            return DataID;
        }

        public void setDataID(int DataID) {
            this.DataID = DataID;
        }

        public String getDataText() {
            return DataText;
        }

        public void setDataText(String DataText) {
            this.DataText = DataText;
        }

        public int getOrderBy() {
            return OrderBy;
        }

        public void setOrderBy(int OrderBy) {
            this.OrderBy = OrderBy;
        }
    }
}
