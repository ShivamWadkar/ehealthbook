import { signUpUser, SignUpRequest } from '../services/signUpService.ts';
import { signUpErrorAction, signUpLoadingAction, signUpUpdateAction } from './signUpSlice.ts';

// Action creator to sign up a user
export const signUpAction = (signUpRequest: SignUpRequest) => async (dispatch: any): Promise<void> => {
  dispatch(signUpLoadingAction())
  void signUpUser(
    signUpRequest,
    (apiResponse) => {
      dispatch(
        signUpUpdateAction({
          data: apiResponse
        })
      )
    },
    (errorMessage, httpStatus) => {
      dispatch(signUpErrorAction({ errorMessage, httpStatus}))
    }
  )
}
