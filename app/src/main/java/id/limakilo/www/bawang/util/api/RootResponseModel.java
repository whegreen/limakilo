package id.limakilo.www.bawang.util.api;

import java.util.HashMap;
import java.util.Map;

public class RootResponseModel {

    private Boolean Error;
    private String Message;
    //    private List data = new ArrayList<>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The Error
     */
    public Boolean getError() {
        return Error;
    }

    /**
     *
     * @param Error
     * The Error
     */
    public void setError(Boolean Error) {
        this.Error = Error;
    }

    /**
     *
     * @return
     * The Message
     */
    public String getMessage() {
        return Message;
    }

    /**
     *
     * @param Message
     * The Message
     */
    public void setMessage(String Message) {
        this.Message = Message;
    }

//    /**
//     *
//     * @return
//     * The data
//     */
//    public List getData() {
//        return data;
//    }
//
//    /**
//     *
//     * @param data
//     * The data
//     */
//    public void setData(List data) {
//        this.data = data;
//    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}