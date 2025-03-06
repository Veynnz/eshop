package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    Map<String, String> paymentData;
    String status;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.validateData();

        if (method == null || method.trim().isEmpty() || !method.equals("VOUCHER")) {
            throw new IllegalArgumentException();
        }
        if (paymentData == null) {
            throw new IllegalArgumentException();
        }
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void validateData() {
        boolean isValid = false;
        switch (PaymentMethod.valueOf(method)) {
            case PaymentMethod.VOUCHER:
                isValid = validateVoucherMethod();
                break;
            case PaymentMethod.BANK_TRANSFER:
                isValid = validateBankMethod();
                break;
            default:
                break;
        }
        if (isValid) {
            status = PaymentStatus.SUCCESS.getValue();
        } else {
            status = PaymentStatus.REJECTED.getValue();
        }
    }

    private boolean validateVoucherMethod() {
        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode == null) {
            return false;
        }
        return checkVoucherCode(voucherCode);
    }

    private boolean checkVoucherCode(String voucherCode) {
        if (voucherCode.length() != 16) {
            return false;
        }

        if (!voucherCode.startsWith("ESHOP")) {
            return false;
        }

        String code = voucherCode.substring(5);
        int numericCharCount = 0;
        for (char character : code.toCharArray()) {
            if (Character.isDigit(character)) {
                numericCharCount++;
            }
        }

        return numericCharCount == 8;
    }

    private boolean validateBankMethod() {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");

        boolean isBankValid = bankName != null && !bankName.isEmpty();
        boolean isReferenceCodeValid = referenceCode != null && !referenceCode.isEmpty();
        return isBankValid && isReferenceCodeValid;
    }
}