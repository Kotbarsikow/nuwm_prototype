package com.google.api.client.extensions.java6.auth.oauth2;

import java.util.Scanner;

/* loaded from: classes2.dex */
public abstract class AbstractPromptReceiver implements VerificationCodeReceiver {
    @Override // com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver
    public void stop() {
    }

    @Override // com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver
    public String waitForCode() {
        String nextLine;
        do {
            System.out.print("Please enter code: ");
            nextLine = new Scanner(System.in).nextLine();
        } while (nextLine.isEmpty());
        return nextLine;
    }
}
