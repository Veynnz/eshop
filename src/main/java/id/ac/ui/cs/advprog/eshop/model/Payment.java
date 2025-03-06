package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String method;
    private Map<String, String> paymentData;
    private String status;

    public Payment(String id, String method, Map<String, String> paymentData) {
        if (method == null || method.trim().isEmpty() || !PaymentMethod.contains(method)) {
            throw new IllegalArgumentException("Payment method cannot be null or empty.");
        }
        if (paymentData == null) {
            throw new IllegalArgumentException("Payment data cannot be null.");
        }

        this.id = id;
        this.method = method;
        this.paymentData = paymentData;

        validateData();
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid payment status.");
        }
    }

    private void validateData() {
        boolean isValid = false;

        if (method.equals(PaymentMethod.VOUCHER.getValue())) {
            isValid = validateVoucherMethod();
        } else if (method.equals(PaymentMethod.BANK_TRANSFER.getValue())) {
            isValid = validateBankMethod();
        }

        this.status = isValid ? PaymentStatus.SUCCESS.getValue() : PaymentStatus.REJECTED.getValue();
    }

    private boolean validateVoucherMethod() {
        String voucherCode = paymentData.get("voucherCode");
        return voucherCode != null && checkVoucherCode(voucherCode);
    }

    private boolean checkVoucherCode(String voucherCode) {
        if (voucherCode.length() != 16 || !voucherCode.startsWith("ESHOP")) {
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
        return bankName != null && !bankName.isEmpty() && referenceCode != null && !referenceCode.isEmpty();
    }
}
