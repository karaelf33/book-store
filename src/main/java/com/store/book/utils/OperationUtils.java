package com.store.book.utils;

import com.store.book.dto.GenericDTO;

import java.util.List;

public class OperationUtils {

    private OperationUtils() {

    }



    public static GenericDTO returnMessageHandling(Object object, int resultCode, boolean resultFlag, String resultMessage) {

        GenericDTO genericDTO = new GenericDTO();

        genericDTO.setResultData(object);
        genericDTO.setResultCode(resultCode);
        genericDTO.setResultFlag(resultFlag);
        genericDTO.setResultMessage(resultMessage);

        return genericDTO;
    }
    public static GenericDTO returnMessageHandling(List<Object> objectList, int resultCode, boolean resultFlag, String resultMessage) {
        GenericDTO genericDTO = new GenericDTO();
        genericDTO.setResultData(objectList);
        genericDTO.setResultCode(resultCode);
        genericDTO.setResultFlag(resultFlag);
        genericDTO.setResultMessage(resultMessage);

        return genericDTO;
    }


}
