package com.store.book.utils;

import com.store.book.dto.GenericDTO;

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


}
