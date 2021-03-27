package br.com.namedida.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.namedida.model.RefeicoesModel;
import br.com.namedida.repository.entity.CardapioEntity;
import br.com.namedida.repository.entity.RefeicoesEntity;
import br.com.namedida.repository.entity.UsuarioEntity;
import br.com.namedida.uteis.Uteis;

public class RefeicoesRepository {

	public static int RefeicaoGlobal = 0;

	@Inject
	RefeicoesEntity refeicoesEntity;

	EntityManager entityManager;

	public Date getPegaDataAtual() {
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		calendar.setTime(date);
		return calendar.getTime();
	}

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA REFEICAO
	 * 
	 * @param refeicoesModel
	 */
	public void SalvarNovaRefeicao(RefeicoesModel refeicoesModel) {

		entityManager = Uteis.JpaEntityManager();
		refeicoesEntity = new RefeicoesEntity();

		refeicoesEntity.setTotalalunos(MedidaRepository.TotalAlunosGlobal);
		refeicoesEntity.setData(getPegaDataAtual());
		System.out.println("data refeicao: " + getPegaDataAtual());

		CardapioEntity cardapioEntity = entityManager.find(
				CardapioEntity.class, DiaRepository.CardapioGlobal);
		refeicoesEntity.setCardapioEntity(cardapioEntity);

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class,
				refeicoesModel.getUsuarioModel().getCodigo());
		refeicoesEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(refeicoesEntity);
		RefeicaoGlobal = refeicoesEntity.getCodigo();
	}

}
