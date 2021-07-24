import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { defaultValue, ICurrentUsager } from "../../model/CurrentUsagerModel";
import type { AppThunk, RootState } from "../store";

interface CurrentUsagerState {
  entity: ICurrentUsager;
  loading: boolean;
  updating: boolean;
  updateSuccess: boolean;
  errorMessage: string | undefined;
}

const initialState: Readonly<CurrentUsagerState> = {
  entity: defaultValue,
  loading: false,
  updating: false,
  updateSuccess: false,
  errorMessage: undefined,
};

// Reducer

const currentUsagerSlice = createSlice({
  name: "currentUsager",
  initialState,
  reducers: {
    requestGetEntity: (state) => {
      state.loading = true;
      state.updateSuccess = false;
      state.errorMessage = undefined;
    },
    failure: (state, action) => {
      state.loading = false;
      state.updating = false;
      state.updateSuccess = false;
      state.errorMessage = action.payload;
    },
    successGetEntity: (state, action) => {
      state.entity = action.payload.data;
      state.loading = false;
    },
    resetState: (state) => {
      state.entity = defaultValue;
      state.loading = false;
      state.updating = false;
      state.updateSuccess = false;
      state.errorMessage = undefined;
    },
  },
});

export const baseUrl = "/usager";

const { requestGetEntity, failure, successGetEntity, resetState } = currentUsagerSlice.actions;

// Actions

export const getEntity = (): AppThunk => async (dispatch: any) => {
  dispatch(requestGetEntity);
  try {
    await axios.get<ICurrentUsager>(`${baseUrl}/current`).then((response) => dispatch(successGetEntity(response)));
  } catch (e) {
    dispatch(failure(e.message));
  }
};

export const reset = () => (dispatch: any) => {
  dispatch(resetState);
};

// Selectors

export const entity = (state: RootState) => state.currentUsager.entity;
export const loading = (state: RootState) => state.currentUsager.loading;
export const updating = (state: RootState) => state.currentUsager.updating;
export const updateSuccess = (state: RootState) => state.currentUsager.updateSuccess;
export const errorMessage = (state: RootState) => state.currentUsager.errorMessage;

export default currentUsagerSlice.reducer;
