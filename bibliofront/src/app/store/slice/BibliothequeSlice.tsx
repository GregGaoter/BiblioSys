import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { defaultValue, IBibliotheque } from "../../model/BibliothequeModel";
import type { AppThunk, RootState } from "../store";

interface BibliothequeState {
  entity: IBibliotheque;
  entities: ReadonlyArray<IBibliotheque>;
  loading: boolean;
  updating: boolean;
  updateSuccess: boolean;
  errorMessage: string | undefined;
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
    requestGetEntityEntities: (state) => {
      state.loading = true;
      state.updateSuccess = false;
      state.errorMessage = undefined;
    },
    requestCreateUpdateDeleteEntity: (state) => {
      state.updating = true;
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
    successGetEntities: (state, action) => {
      state.entities = action.payload.data;
      state.loading = false;
    },
    successCreateUpdateEntity: (state, action) => {
      state.entity = action.payload.data;
      state.updating = false;
      state.updateSuccess = true;
    },
    successDeleteEntity: (state) => {
      state.entity = {};
      state.updating = false;
      state.updateSuccess = true;
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

const {
  requestGetEntityEntities,
  requestCreateUpdateDeleteEntity,
  failure,
  successGetEntity,
  successGetEntities,
  successCreateUpdateEntity,
  successDeleteEntity,
  resetState,
} = bibliothequeSlice.actions;

// Actions

export const getEntity =
  (id: number): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestGetEntityEntities);
    try {
      await axios.get<IBibliotheque>(`${baseUrl}/${id}`).then((response) => dispatch(successGetEntity(response)));
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const getEntities = (): AppThunk => async (dispatch: any) => {
  dispatch(requestGetEntityEntities);
  try {
    await axios.get<IBibliotheque>(`${baseUrl}/all`).then((response) => dispatch(successGetEntities(response)));
  } catch (e) {
    dispatch(failure(e.message));
  }
};

export const createEntity =
  (entity: IBibliotheque): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestCreateUpdateDeleteEntity);
    try {
      const response = await axios.post<IBibliotheque>(`${baseUrl}`, entity);
      dispatch(successCreateUpdateEntity(response));
      await dispatch(getEntities());
      return response;
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const updateEntity =
  (id: number, entity: IBibliotheque): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestCreateUpdateDeleteEntity);
    try {
      await axios
        .put<IBibliotheque>(`${baseUrl}/${id}`, entity)
        .then((response) => dispatch(successCreateUpdateEntity(response)));
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const deleteEntity =
  (id: number): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestCreateUpdateDeleteEntity);
    try {
      const response = await axios.delete<IBibliotheque>(`${baseUrl}`);
      dispatch(successDeleteEntity);
      await dispatch(getEntities());
      return response;
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const reset = () => (dispatch: any) => {
  dispatch(resetState);
};

// Selectors

export const entity = (state: RootState) => state.bibliotheque.entity;
export const entities = (state: RootState) => state.bibliotheque.entities;
export const loading = (state: RootState) => state.bibliotheque.loading;
export const updating = (state: RootState) => state.bibliotheque.updating;
export const updateSuccess = (state: RootState) => state.bibliotheque.updateSuccess;
export const errorMessage = (state: RootState) => state.bibliotheque.errorMessage;

export default bibliothequeSlice.reducer;
