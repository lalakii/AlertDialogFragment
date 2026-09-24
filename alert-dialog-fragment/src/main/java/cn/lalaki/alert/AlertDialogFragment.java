// SPDX short identifier: MIT
package cn.lalaki.alert;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public abstract class AlertDialogFragment extends DialogFragment {

    private AlertDialog mDialog;
    private DialogInterface.OnCancelListener mOnCancelListener;
    private DialogInterface.OnDismissListener mOnDismissListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog dialog = mDialog;
        if (dialog == null) {
            return super.onCreateDialog(savedInstanceState);
        }
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        final AlertDialog dialog = mDialog;
        if (dialog == null) {
            dismiss();
        }
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        final DialogInterface.OnCancelListener onCancelListener = mOnCancelListener;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialog);
        }
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        final DialogInterface.OnDismissListener onDismissListener = mOnDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }

    @Nullable
    @Override
    public Dialog getDialog() {
        final AlertDialog dialog = mDialog;
        if (dialog == null) {
            return super.getDialog();
        }
        return dialog;
    }

    private void setAlertDialog(AlertDialog dialog, DialogInterface.OnCancelListener onCancelListener, DialogInterface.OnDismissListener onDismissListener) {
        this.mDialog = dialog;
        this.mOnCancelListener = onCancelListener;
        this.mOnDismissListener = onDismissListener;
    }

    public final static class Builder extends AlertDialog.Builder {
        private static class AlertDialogEx extends AlertDialogFragment {
        }

        private final Context mContext;
        private DialogInterface.OnDismissListener mOnDismissListener;
        private DialogInterface.OnCancelListener mOnCancelListener;

        public Builder(final Context context) {
            super(context);
            this.mContext = context;
        }

        @Override
        public AlertDialog create() {
            final AlertDialogFragment dialog = new AlertDialogEx();
            dialog.setAlertDialog(super.create(), mOnCancelListener, mOnDismissListener);
            if (mContext instanceof FragmentActivity) {
                final FragmentManager fragmentManager = ((FragmentActivity) mContext).getSupportFragmentManager();
                if (!fragmentManager.isDestroyed()) {
                    dialog.show(fragmentManager, String.valueOf(System.nanoTime()));
                }
            }
            return (AlertDialog) dialog.getDialog();
        }

        @Override
        public AlertDialog.Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.mOnCancelListener = onCancelListener;
            return super.setOnCancelListener(onCancelListener);
        }

        @Override
        public AlertDialog.Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            this.mOnDismissListener = onDismissListener;
            return super.setOnDismissListener(onDismissListener);
        }

        @Override
        public AlertDialog show() {
            return create();
        }
    }
}
