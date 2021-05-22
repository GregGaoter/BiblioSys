import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { defaultValue, IBibliotheque } from "../../model/BibliothequeModel";
import type { RootState } from "../store";

// const ACTION_TYPES = {
//   FETCH_BIBLIOTHEQUE_LIST: "bibliotheque/FETCH_BIBLIOTHEQUE_LIST",
//   FETCH_BIBLIOTHEQUE: "bibliotheque/FETCH_BIBLIOTHEQUE",
//   CREATE_BIBLIOTHEQUE: "bibliotheque/CREATE_BIBLIOTHEQUE",
//   UPDATE_BIBLIOTHEQUE: "bibliotheque/UPDATE_BIBLIOTHEQUE",
//   DELETE_BIBLIOTHEQUE: "bibliotheque/DELETE_BIBLIOTHEQUE",
//   RESET: "bibliotheque/RESET",
// };

interface BibliothequeState {
  loading: boolean;
  errorMessage: string | undefined;
  entities: ReadonlyArray<IBibliotheque>;
  entity: IBibliotheque;
  updating: boolean;
  updateSuccess: boolean;
}

const initialState: Readonly<BibliothequeState> = {
  entity: defaultValue,
  entities: [],
  loading: false,
  updating: false,
  updateSuccess: false,
  errorMessage: undefined,
};

// Reducer

const bibliothequeSlice = createSlice({
  name: "bibliotheque",
  initialState,
  reducers: {
    requestFetchEntities: (state) => {
      state.loading = true;
      state.updateSuccess = false;
      state.errorMessage = undefined;
    },
    failureFetchEntities: (state, action) => {
      state.loading = false;
      state.updating = false;
      state.updateSuccess = false;
      state.errorMessage = action.payload;
    },
    successFetchEntities: (state, action) => {
      state.loading = false;
      state.entities = action.payload.data;
    },
    resetState: (state) => {
      state.entity = defaultValue;
      state.entities = [];
      state.loading = false;
      state.updating = false;
      state.updateSuccess = false;
      state.errorMessage = undefined;
    },
  },
});

export const baseUrl = "/bibliotheque";

const { requestFetchEntities, failureFetchEntities, successFetchEntities, resetState } = bibliothequeSlice.actions;

// Actions

// export const getEntities = createAsyncThunk(ACTION_TYPES.FETCH_BIBLIOTHEQUE_LIST, async () => {
//   const response = await axios.get<IBibliotheque>(`${baseUrl}/all`);
//   return response.data;
// });

export const getEntities = () => async (dispatch: any) => {
  dispatch(requestFetchEntities);
  try {
    await axios.get<IBibliotheque>(`${baseUrl}/all`).then((response) => dispatch(successFetchEntities(response)));
  } catch (e) {
    dispatch(failureFetchEntities(e.message));
  }
};

export const reset = () => (dispatch: any) => {
  dispatch(resetState);
};

// Selectors

export const selectEntities = (state: RootState) => state.bibliotheque.entities;

export default bibliothequeSlice.reducer;
