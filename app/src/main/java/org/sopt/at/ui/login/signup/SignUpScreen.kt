package org.sopt.at.ui.login.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.at.R
import org.sopt.at.ui.login.signup.components.SignUpInputStep
import org.sopt.at.utils.RegexUtils
import org.sopt.at.utils.SharedPreferencesManager

@Composable
fun SignUpScreen(
    navigateToSignInScreen: () -> Unit,
    viewModel: SignUpViewModel = viewModel(),
) {

    val step by viewModel.step.collectAsState()
    val userId by viewModel.uerId.collectAsState()
    val nickname by viewModel.nickname.collectAsState()
    val password by viewModel.password.collectAsState()


    when (step) {
        SignUpStep.ID -> {
            SignUpInputStep(
                title = stringResource(R.string.sign_up_id_title),
                hint = stringResource(R.string.tf_id),
                value = userId,
                onValueChange = viewModel::updateUserId,
                errorMessage = stringResource(R.string.sign_up_id_rule),
                isError = !RegexUtils.isValidId(userId),
                onNextClick = viewModel::nextStep,
            )
        }

        SignUpStep.NICKNAME -> {
            SignUpInputStep(
                title = stringResource(R.string.sign_up_nickname_title),
                hint = stringResource(R.string.tf_nickname), // need to change
                value = nickname,
                onValueChange = viewModel::updateNickname,
                errorMessage = stringResource(R.string.sign_up_id_rule),
                isError = !RegexUtils.isValidId(nickname), // need to change
                onNextClick = viewModel::nextStep,
            )
        }

        SignUpStep.PASSWORD -> {

            SignUpInputStep(
                title = stringResource(R.string.sign_up_password_title),
                hint = stringResource(R.string.tf_password),
                value = password,
                onValueChange = viewModel::updatePassword,
                errorMessage = stringResource(R.string.sign_up_password_rule),
                isError = !RegexUtils.isValidPassword(password),
                onNextClick = viewModel::nextStep,
                isPassword = true,
            )
        }

        SignUpStep.COMPLETE -> {
            SharedPreferencesManager.registerUser(userId, password)
            navigateToSignInScreen()
        }
    }
}
