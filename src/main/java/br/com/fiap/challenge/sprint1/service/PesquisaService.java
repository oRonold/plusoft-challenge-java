package br.com.fiap.challenge.sprint1.service;

import br.com.fiap.challenge.sprint1.infra.exception.PesquisaIdNotFound;
import br.com.fiap.challenge.sprint1.infra.exception.ValidacaoException;
import br.com.fiap.challenge.sprint1.model.categoria.Categoria;
import br.com.fiap.challenge.sprint1.model.figuraPublica.FiguraPublica;
import br.com.fiap.challenge.sprint1.model.pesquisa.Pesquisa;
import br.com.fiap.challenge.sprint1.model.pesquisa.StatusPesquisa;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.AdicionarFigPublicaDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.CriarPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.ListagemPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.tipoServico.TipoServico;
import br.com.fiap.challenge.sprint1.model.usuario.Usuario;
import br.com.fiap.challenge.sprint1.repository.PesquisaRepository;
import br.com.fiap.challenge.sprint1.repository.UsuarioRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PesquisaService {

    @Autowired
    private PesquisaRepository pesquisaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Pesquisa criarPesquisa(CriarPesquisaDTO dto){
        var usuario = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var pesquisa = new Pesquisa(dto);
        var tipServico = new TipoServico(dto);
        var figuraPub = new FiguraPublica(dto);
        var categoria = new Categoria(dto);

        pesquisa.setUsuario((Usuario) usuario);
        ((Usuario) usuario).getPesquisas().add(pesquisa);
        pesquisa.setTipoServico(tipServico);
        pesquisa.getFiguraPublica().add(figuraPub);

        figuraPub.getPesquisa().add(pesquisa);
        figuraPub.setCategoria(categoria);

        pesquisaRepository.save(pesquisa);
        return pesquisa;
    }

    public Pesquisa addFiguraPublica(Long idPesquisa, AdicionarFigPublicaDTO dto){
        if(!pesquisaRepository.existsById(idPesquisa)){
            throw new ValidacaoException("A pesquisa não existe!");
        }
        var pesquisa = pesquisaRepository.getReferenceById(idPesquisa);
        var figPublica = new FiguraPublica(dto);
        var categoria = new Categoria(dto);

        pesquisa.getFiguraPublica().add(figPublica);
        figPublica.setCategoria(categoria);

        return pesquisa;
    }

    public List<ListagemPesquisaDTO> listarPesquisaUsuario(){
        var auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return pesquisaRepository.findAllByUsuario((Usuario) auth).stream().map(ListagemPesquisaDTO::new).toList();
    }

    public Pesquisa buscarPorId(Long id){
        var usuario = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var pesquisa = pesquisaRepository.findByCodigoAndUsuario(id, (Usuario) usuario);
        if(pesquisa == null){
            throw new PesquisaIdNotFound();
        }
        return pesquisa;
    }

    public Pesquisa excluir(Long id){
        var usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var pesquisa = pesquisaRepository.findByCodigoAndUsuario(id, (Usuario) usuario);
        pesquisa.setStatusPesquisa(StatusPesquisa.CONCLUIDA);
        return pesquisa;
    }

}
