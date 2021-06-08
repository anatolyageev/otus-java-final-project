package ru.otus.ageev.web.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.log4j.Logger;

public class PrettyFacesWrappedRequest extends HttpServletRequestWrapper {
    final static Logger LOG = Logger.getLogger(PrettyFacesWrappedRequest.class);

    private final Map<String, String[]> modifiableParameters;


    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request the {@link HttpServletRequest} to be wrapped.
     * @throws IllegalArgumentException if the request is null
     */
    public PrettyFacesWrappedRequest(HttpServletRequest request,
                                     final Map<String, String[]> additionalParams) {
        super(request);
        Map<String, String[]> parameterMap = super.getParameterMap();
        parameterMap.forEach((key, value) -> {

            LOG.debug("key: " + key + " value: " + Arrays.toString(value));
        });
        List<String> paramToDel = removeNullParams(parameterMap);

        for (String s : paramToDel) {
            parameterMap.remove(s);
        }
        modifiableParameters = new TreeMap<String, String[]>();
        modifiableParameters.putAll(parameterMap);
    }

    private List<String> removeNullParams(Map<String, String[]> parameterMap) {
        List<String> nullParam = new ArrayList<>();

        for (Map.Entry<String, String[]> es : parameterMap.entrySet()) {
            if (Objects.isNull(es.getValue())) {
                nullParam.add(es.getKey());
            }
        }
        return nullParam;
    }

}
